package com.Neoflex.conveyor.service;


import com.Neoflex.conveyor.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ConveyorService {

    @Value("${standard.rate}")
    private double rate;
    private static final Integer NUMBER_OF_ROUNDED_CHARACTERS = 5;
    private long applicationId = 0;

    public List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = new ArrayList<>();
        applicationId++;
        generateStandardLoanOfferDTO(loanApplicationRequestDTO, offers);
        generateLoanOfferDTOWithInsuranceEnabled(loanApplicationRequestDTO, offers);
        generateLoanOfferDTOForSalaryClient(loanApplicationRequestDTO, offers);
        generateLoanOfferDTOForSalaryClientWithInsuranceEnabled(loanApplicationRequestDTO, offers);
        log.info("getOffers method return: {}", offers);
        return offers;
    }

    private BigDecimal getInsurance(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return loanApplicationRequestDTO.getAmount()
                .divide(BigDecimal.valueOf(100), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(2))
                .multiply(BigDecimal.valueOf(loanApplicationRequestDTO.getTerm()))
                .divide(BigDecimal.valueOf(12), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
    }

    private void generateLoanOfferDTOWithInsuranceEnabled(LoanApplicationRequestDTO loanApplicationRequestDTO, List<LoanOfferDTO> offers) {
        BigDecimal insurance = getInsurance(loanApplicationRequestDTO);
        BigDecimal finalRate = BigDecimal.valueOf(rate).subtract(BigDecimal.valueOf(1.5));
        BigDecimal totalAmount = loanApplicationRequestDTO.getAmount().add(insurance);
        BigDecimal monthlyInterestRate = finalRate.divide(BigDecimal.valueOf(1200), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal x = monthlyInterestRate.add(BigDecimal.valueOf(1))
                .pow(loanApplicationRequestDTO.getTerm());
        BigDecimal y = BigDecimal.valueOf(1).divide(x, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal z = BigDecimal.valueOf(1).subtract(y);
        BigDecimal monthlyPayment = monthlyInterestRate.divide(z, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP)
                .multiply(totalAmount);
        LoanOfferDTO loanOfferDTO1 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), totalAmount,
                loanApplicationRequestDTO.getTerm(), monthlyPayment, finalRate, true, false);
        offers.add(loanOfferDTO1);
        applicationId++;
    }

    private void generateLoanOfferDTOForSalaryClientWithInsuranceEnabled(LoanApplicationRequestDTO loanApplicationRequestDTO, List<LoanOfferDTO> offers) {
        BigDecimal insurance = getInsurance(loanApplicationRequestDTO);
        BigDecimal finalRate = BigDecimal.valueOf(rate).subtract(BigDecimal.valueOf(3));
        BigDecimal totalAmount = loanApplicationRequestDTO.getAmount().add(insurance);
        BigDecimal monthlyInterestRate = finalRate.divide(BigDecimal.valueOf(1200), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal x = monthlyInterestRate.add(BigDecimal.valueOf(1))
                .pow(loanApplicationRequestDTO.getTerm());
        BigDecimal y = BigDecimal.valueOf(1).divide(x, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal z = BigDecimal.valueOf(1).subtract(y);
        BigDecimal monthlyPayment = monthlyInterestRate.divide(z, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP)
                .multiply(totalAmount);
        LoanOfferDTO loanOfferDTO2 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), totalAmount,
                loanApplicationRequestDTO.getTerm(), monthlyPayment, finalRate, true, true);
        offers.add(loanOfferDTO2);
        applicationId++;
    }

    private void generateStandardLoanOfferDTO(LoanApplicationRequestDTO loanApplicationRequestDTO, List<LoanOfferDTO> offers) {
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(rate)
                .divide(BigDecimal.valueOf(1200), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal x = monthlyInterestRate.add(BigDecimal.valueOf(1))
                .pow(loanApplicationRequestDTO.getTerm());
        BigDecimal y = BigDecimal.valueOf(1).divide(x, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal z = BigDecimal.valueOf(1).subtract(y);
        BigDecimal monthlyPayment = monthlyInterestRate.divide(z, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP)
                .multiply(loanApplicationRequestDTO.getAmount());
        LoanOfferDTO loanOfferDTO3 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), monthlyPayment, BigDecimal.valueOf(rate), false, false);
        offers.add(loanOfferDTO3);
        applicationId++;
    }

    private void generateLoanOfferDTOForSalaryClient(LoanApplicationRequestDTO loanApplicationRequestDTO, List<LoanOfferDTO> offers) {
        BigDecimal finalRate = BigDecimal.valueOf(rate).subtract(BigDecimal.valueOf(1.5));
        BigDecimal monthlyInterestRate = finalRate.divide(BigDecimal.valueOf(1200), NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal x = monthlyInterestRate.add(BigDecimal.valueOf(1))
                .pow(loanApplicationRequestDTO.getTerm());
        BigDecimal y = BigDecimal.valueOf(1).divide(x, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP);
        BigDecimal z = BigDecimal.valueOf(1).subtract(y);
        BigDecimal monthlyPayment = monthlyInterestRate.divide(z, NUMBER_OF_ROUNDED_CHARACTERS, RoundingMode.HALF_UP)
                .multiply(loanApplicationRequestDTO.getAmount());
        LoanOfferDTO loanOfferDTO4 = new LoanOfferDTO(applicationId, loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getAmount(),
                loanApplicationRequestDTO.getTerm(), monthlyPayment, finalRate, false, true);
        offers.add(loanOfferDTO4);
        applicationId++;
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
            double finalRate = rate;

            switch (scoringDataDTO.getEmployment().getEmploymentStatus().toString()) {
                case ("SELF_EMPLOYED"):
                    finalRate++;
                    break;
                case ("BUSINESS_OWNER"):
                    finalRate += 3;
                    break;
                case ("EMPLOYED"):
                    switch (scoringDataDTO.getEmployment().getPosition().toString()) {

                        case ("MID_MANAGER"):
                            finalRate -= 2;
                            break;
                        case ("TOP_MANAGER"):
                            finalRate -= 4;
                            break;
                        default:
                            break;
                    }
            }

            switch (scoringDataDTO.getMaritalStatus().toString()) {

                case ("MARRIED"):
                    finalRate -= 3;
                    break;
                case ("DIVORCED"):
                    finalRate += 1;
                    break;
                default:
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
                psk = monthlyPayment.multiply(BigDecimal.valueOf(scoringDataDTO.getTerm()))
                        .divide(total).subtract(BigDecimal.valueOf(1))
                        .divide(BigDecimal.valueOf(scoringDataDTO.getTerm()))
                        .divide(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(100));
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
                        paymentOfInterest, debtPayment, remainingDebt);
                paymentSchedule.add(paymentScheduleElement);
            }

            CreditDTO creditDTO = new CreditDTO(scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), monthlyPayment, BigDecimal.valueOf(finalRate), psk,
                    scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient(), paymentSchedule);
            return creditDTO;
        }
    }
}
