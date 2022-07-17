package com.Neoflex.conveyor.service;

import com.Neoflex.conveyor.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConveyorServiceTest {

    @Autowired
    ConveyorService conveyorService;

    @Test
    void getOffers_SuccessTest_withMinValues() {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        List<LoanOfferDTO> offers = conveyorService.getOffers(loanApplicationRequestDTO);
        assertEquals(4, offers.size());
        for (var offer : offers) {
            assertNotNull(offer.getApplicationId());
            assertEquals(loanApplicationRequestDTO.getAmount(), offer.getRequestedAmount());
            assertEquals(loanApplicationRequestDTO.getTerm(), offer.getTerm());
            if (!offer.isInsuranceEnabled() && !offer.isSalaryClient()) {
                assertTrue(BigDecimal.valueOf(10000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(1745).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(16).subtract(offer.getRate().abs()).compareTo(BigDecimal.ZERO.stripTrailingZeros()) <= 0);
            }
            if (offer.isInsuranceEnabled() && !offer.isSalaryClient()) {
                assertTrue(BigDecimal.valueOf(10100).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(1755).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(14.5).subtract(offer.getRate().abs()).compareTo(BigDecimal.ZERO.stripTrailingZeros()) <= 0);
            }
            if (offer.isSalaryClient() && !offer.isInsuranceEnabled()) {
                assertTrue(BigDecimal.valueOf(10000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(1738).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(14.5).subtract(offer.getRate().abs()).compareTo(BigDecimal.ZERO.stripTrailingZeros()) <= 0);
            }
            if (offer.isSalaryClient() && offer.isInsuranceEnabled()) {
                assertTrue(BigDecimal.valueOf(10100).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(1748).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(13).subtract(offer.getRate().abs()).compareTo(BigDecimal.ZERO.stripTrailingZeros()) <= 0);
            }
            assertTrue(offers.get(1).getRate().compareTo(offers.get(0).getRate()) < 0);
        }
    }

    @Test
    void getOffers_SuccessTest_withMaxValues() {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000000));
        loanApplicationRequestDTO.setTerm(60);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        List<LoanOfferDTO> offers = conveyorService.getOffers(loanApplicationRequestDTO);
        assertEquals(4, offers.size());
        for (var offer : offers) {
            assertNotNull(offer.getApplicationId());
            assertEquals(loanApplicationRequestDTO.getAmount(), offer.getRequestedAmount());
            assertEquals(loanApplicationRequestDTO.getTerm(), offer.getTerm());
            if (!offer.isInsuranceEnabled() && !offer.isSalaryClient()) {
                assertTrue(BigDecimal.valueOf(10000000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(243181).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(16).subtract(offer.getRate().abs()).compareTo(BigDecimal.valueOf(0)) <= 0);
            }
            if (offer.isInsuranceEnabled() && !offer.isSalaryClient()) {
                assertTrue(BigDecimal.valueOf(11000000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(258811).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(14.5).subtract(offer.getRate().abs()).compareTo(BigDecimal.valueOf(0)) <= 0);
            }
            if (offer.isSalaryClient() && !offer.isInsuranceEnabled()) {
                assertTrue(BigDecimal.valueOf(10000000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(235283).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(14.5).subtract(offer.getRate().abs()).compareTo(BigDecimal.valueOf(0)) <= 0);
            }
            if (offer.isSalaryClient() && offer.isInsuranceEnabled()) {
                assertTrue(BigDecimal.valueOf(11000000).subtract(offer.getTotalAmount()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(250284).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(13).subtract(offer.getRate().abs()).compareTo(BigDecimal.valueOf(0)) <= 0);
            }
            assertTrue(offers.get(1).getRate().compareTo(offers.get(0).getRate()) < 0);
        }
    }

    @Test
    void getCredit_SuccessTest_withMinValues() {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("firstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6662");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(10000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount("Account");
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);

        assertEquals(scoringDataDTO.getTerm(), conveyorService.getCredit(scoringDataDTO).getTerm());
       assertTrue(BigDecimal.valueOf(1775).subtract(conveyorService.getCredit(scoringDataDTO).getMonthlyPayment())
               .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
       assertTrue(BigDecimal.valueOf(10558).subtract(conveyorService.getCredit(scoringDataDTO).getAmount())
               .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
        assertTrue(BigDecimal.valueOf(3).subtract(conveyorService.getCredit(scoringDataDTO).getRate())
                .abs().compareTo(BigDecimal.ZERO) <= 0);
        assertTrue(BigDecimal.valueOf(10650).subtract(conveyorService.getCredit(scoringDataDTO).getPsk())
                .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
        assertEquals(scoringDataDTO.getIsInsuranceEnabled(), conveyorService.getCredit(scoringDataDTO).getIsInsuranceEnabled());
        assertEquals(scoringDataDTO.getIsSalaryClient(), conveyorService.getCredit(scoringDataDTO).getIsSalaryClient());

        List<PaymentScheduleElement> paymentSchedule = conveyorService.getCredit(scoringDataDTO).getPaymentSchedule();

        for (int i = 0; i < scoringDataDTO.getTerm(); i++) {
            if (i==0) {
                assertTrue(BigDecimal.valueOf(1775).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(26.4).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(5))<=0);
                assertTrue(BigDecimal.valueOf(1749).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(8809).subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
            if (i==2) {
                assertTrue(BigDecimal.valueOf(1775).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(17.6).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(5))<=0);
                assertTrue(BigDecimal.valueOf(1757).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(5299).subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
            if (i==5) {
                assertTrue(BigDecimal.valueOf(1775).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(4.4).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(5))<=0);
                assertTrue(BigDecimal.valueOf(1770.5).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.ONE.subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
       }
    }
    @Test
    void getCredit_SuccessTest_withMaxValues() {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000000));
        scoringDataDTO.setTerm(60);
        scoringDataDTO.setFirstName("firstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.NON_BINARY);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6662");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.DIVORCED);
        scoringDataDTO.setDependentAmount(2);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.BUSINESS_OWNER, "123456789112", BigDecimal.valueOf(500000), Position.OWNER, 46, 46));
        scoringDataDTO.setAccount("Account");
        scoringDataDTO.setIsInsuranceEnabled(false);
        scoringDataDTO.setIsSalaryClient(false);
        assertEquals(scoringDataDTO.getTerm(), conveyorService.getCredit(scoringDataDTO).getTerm());
        assertTrue(BigDecimal.valueOf(632895).subtract(conveyorService.getCredit(scoringDataDTO).getMonthlyPayment())
                .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
        assertTrue(BigDecimal.valueOf(22000000).subtract(conveyorService.getCredit(scoringDataDTO).getAmount())
                .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
        assertTrue(BigDecimal.valueOf(24).subtract(conveyorService.getCredit(scoringDataDTO).getRate())
                .abs().compareTo(BigDecimal.ZERO) <= 0);
        assertTrue(BigDecimal.valueOf(37973700).subtract(conveyorService.getCredit(scoringDataDTO).getPsk())
                .abs().compareTo(BigDecimal.valueOf(50)) <= 0);
       assertEquals(scoringDataDTO.getIsInsuranceEnabled(), conveyorService.getCredit(scoringDataDTO).getIsInsuranceEnabled());
          assertEquals(scoringDataDTO.getIsSalaryClient(), conveyorService.getCredit(scoringDataDTO).getIsSalaryClient());

        List<PaymentScheduleElement> paymentSchedule = conveyorService.getCredit(scoringDataDTO).getPaymentSchedule();

        for (int i = 0; i < scoringDataDTO.getTerm(); i++) {
            if (i==0) {
                assertTrue(BigDecimal.valueOf(632895).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(440000).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(192895).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(21807105).subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
            if (i==29) {
                assertTrue(BigDecimal.valueOf(632895).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(290343).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(342552).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(14174620).subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
            if (i==59) {
                assertTrue(BigDecimal.valueOf(632895).subtract(paymentSchedule.get(i).getTotalPayment()).abs().compareTo(BigDecimal.valueOf(50)) <= 0);
                assertTrue(BigDecimal.valueOf(12410).subtract(paymentSchedule.get(i).getInterestPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(620485).subtract(paymentSchedule.get(i).getDebtPayment()).abs().compareTo(BigDecimal.valueOf(50))<=0);
                assertTrue(BigDecimal.valueOf(28).subtract(paymentSchedule.get(i).getRemainingDebt()).abs().compareTo(BigDecimal.valueOf(50))<=0);
            }
        }
    }
}
