package com.Neoflex.conveyor.service;


import com.Neoflex.conveyor.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
@Service
public class ConveyorService {
    private final int STANDARD_RATE = 16;
    private long applicationId = 0;

    public List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = new ArrayList<>();
        BigDecimal insuranceСost = loanApplicationRequestDTO.getAmount().divide(BigDecimal.valueOf(100))
                .multiply(BigDecimal.valueOf(2.5)).multiply(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm())).divide(BigDecimal.valueOf(12));

        applicationId++;
        BigDecimal paymentOfInterest = loanApplicationRequestDTO.getAmount().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(STANDARD_RATE))
                .divide(BigDecimal.valueOf(12));
        BigDecimal monthlyPayment = loanApplicationRequestDTO.getAmount().divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm())).
                add(paymentOfInterest);
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), monthlyPayment, BigDecimal.valueOf(STANDARD_RATE), false, false );
        offers.add(loanOfferDTO);

        applicationId++;
        BigDecimal monthlyPayment1 = loanApplicationRequestDTO.getAmount().divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()));
        BigDecimal totalRate1 = BigDecimal.valueOf(STANDARD_RATE).subtract(BigDecimal.valueOf(1.5));
        LoanOfferDTO loanOfferDTO1 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), monthlyPayment1, totalRate1, false, true );
        offers.add(loanOfferDTO1);

        applicationId++;
        BigDecimal totalAmount = loanApplicationRequestDTO.getAmount().add(insuranceСost);
        BigDecimal monthlyPayment2 = totalAmount.divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()));
        BigDecimal totalRate2 = BigDecimal.valueOf(STANDARD_RATE).subtract(BigDecimal.valueOf(1.5));
        LoanOfferDTO loanOfferDTO2 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), totalAmount,
                loanApplicationRequestDTO.getTerm(), monthlyPayment2, totalRate2, true, false );
        offers.add(loanOfferDTO2);

        applicationId++;
        BigDecimal totalAmount2 = loanApplicationRequestDTO.getAmount().add(insuranceСost);
        BigDecimal monthlyPayment3 = totalAmount.divide(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()));
        BigDecimal totalRate3 = BigDecimal.valueOf(STANDARD_RATE).subtract(BigDecimal.valueOf(3));
        LoanOfferDTO loanOfferDTO3 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), totalAmount2,
                loanApplicationRequestDTO.getTerm(), monthlyPayment3, totalRate3, true, true );
        offers.add(loanOfferDTO3);

        return offers;
    }

    public CreditDTO getCredit(ScoringDataDTO scoringDataDTO) {
        BigDecimal insuranceСost = scoringDataDTO.getAmount().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2.5))
                .multiply(BigDecimal.valueOf(scoringDataDTO.getTerm())).divide(BigDecimal.valueOf(12));
        Period period = Period.between(LocalDate.now(), scoringDataDTO.getBirthdate());
        int ageClient = Math.abs(period.getYears());

        if (scoringDataDTO.getEmployment().getEmploymentStatus().toString().equals("UNEMPLOYED") ||
                scoringDataDTO.getAmount().compareTo(scoringDataDTO.getEmployment().getSalary().multiply(BigDecimal.valueOf(20))) < 0 ||
                ageClient < 20 || ageClient > 60 || scoringDataDTO.getEmployment().getWorkExperienceTotal() < 12 ||
                scoringDataDTO.getEmployment().getWorkExperienceCurrent() < 3) {
            return null;
        } else {
            double finalRate = STANDARD_RATE;

            switch (scoringDataDTO.getEmployment().getEmploymentStatus().toString()) {
                case ("SELF_EMPLOYED"):
                    finalRate++;
                    break;
                case ("BUSINESS_OWNER"):
                    finalRate += 3;
                    break;
                case ("EMPLOYED"):
                    switch (scoringDataDTO.getEmployment().getPosition().toString()) {
                        case ("WORKER"):
                            break;
                        case ("MID_MANAGER"):
                            finalRate -= 2;
                            break;
                        case ("TOP_MANAGER"):
                            finalRate -= 4;
                            break;
                        case ("OWNER"):
                            finalRate += 5;
                            break;
                    }
            }

            switch (scoringDataDTO.getMaritalStatus().toString()) {
                case ("SINGLE"):
                    break;
                case ("MARRIED"):
                    finalRate -= 3;
                    break;
                case ("DIVORCED"):
                    finalRate += 1;
                    break;
                case ("WIDOW_WIDOWER"):
                    break;
            }

            if (scoringDataDTO.getDependentAmount() > 1) {
                finalRate++;
            }

            switch (scoringDataDTO.getGender().toString()) {
                case ("MALE"):
                    if (ageClient < 55 && ageClient > 30) {
                        finalRate -= 3;
                    }
                    break;
                case ("FEMALE"):
                    if (ageClient < 60 && ageClient > 35) {
                        finalRate -= 3;
                    }
                    break;
                case ("NON_BINARY"): {
                    finalRate += 3;
                }
            }


            BigDecimal psk;
            BigDecimal monthlyPayment;
            BigDecimal debtPayment;
            BigDecimal paymentOfInterest;
            BigDecimal remainingDebt;

            if (scoringDataDTO.getIsInsuranceEnabled()) {
                if (scoringDataDTO.getIsSalaryClient()) {
                    finalRate -= 3;
                } else {
                    finalRate -= 1.5;
                }

                BigDecimal total = scoringDataDTO.getAmount().add(insuranceСost);
                debtPayment = total.divide(BigDecimal.valueOf(scoringDataDTO.getTerm()));
                paymentOfInterest = total.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(finalRate));
                monthlyPayment = debtPayment.add(paymentOfInterest);
                psk = monthlyPayment.multiply(BigDecimal.valueOf(scoringDataDTO.getTerm())).divide(total).subtract(BigDecimal.valueOf(1))
                        .divide(BigDecimal.valueOf(scoringDataDTO.getTerm())).divide(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(100));
                remainingDebt = total;
            } else {
                if (scoringDataDTO.getIsSalaryClient()) {
                    finalRate -= 1.5;
                }
                debtPayment = scoringDataDTO.getAmount().divide(BigDecimal.valueOf(scoringDataDTO.getTerm()));
                paymentOfInterest = scoringDataDTO.getAmount().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(finalRate));
                monthlyPayment = debtPayment.add(paymentOfInterest);
                psk = monthlyPayment.multiply(BigDecimal.valueOf(scoringDataDTO.getTerm())).divide(scoringDataDTO.getAmount()).subtract(BigDecimal.valueOf(1))
                        .divide(BigDecimal.valueOf(scoringDataDTO.getTerm())).divide(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(100));
                remainingDebt = scoringDataDTO.getAmount();
            }

            LocalDate transactionDate = LocalDate.now();
            List<PaymentScheduleElement> paymentSchedule = new ArrayList<>();
            for (int i = 0; i < scoringDataDTO.getTerm(); i++) {
                remainingDebt = remainingDebt.subtract(monthlyPayment);
                PaymentScheduleElement paymentScheduleElement = new PaymentScheduleElement(i + 1, transactionDate.plusMonths(i + 1), monthlyPayment,
                        paymentOfInterest, debtPayment, remainingDebt );
                paymentSchedule.add(paymentScheduleElement);
            }

            CreditDTO creditDTO = new CreditDTO(scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), monthlyPayment, BigDecimal.valueOf(finalRate), psk,
                    scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(),paymentSchedule );
            return creditDTO;
        }
    }
}
