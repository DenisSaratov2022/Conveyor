package com.Neoflex.conveyor.service;

import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.Neoflex.conveyor.model.LoanOfferDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
                assertTrue(BigDecimal.valueOf(250284).subtract(offer.getMonthlyPayment().abs()).compareTo(BigDecimal.valueOf(500)) <= 0);
                assertTrue(BigDecimal.valueOf(13).subtract(offer.getRate().abs()).compareTo(BigDecimal.valueOf(0)) <= 0);
            }
            assertTrue(offers.get(1).getRate().compareTo(offers.get(0).getRate()) < 0);
        }
    }
}