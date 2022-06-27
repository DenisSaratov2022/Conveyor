package com.Neoflex.conveyor.controller;

import com.Neoflex.conveyor.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConveyorControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    @Test
    void controller_ConveyorOffers_SuccessTest() throws Exception {
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
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void controller_ConveyorOffers_BirthdateTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now());
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void controller_ConveyorOffers_AbsenceFirstNameTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName(null);
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now());
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorCalculation_SuccessTest () throws Exception {
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
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isOk());
    }
    @Test
    void controller_ConveyorCalculation_SmallAmountTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(1000));
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
        mockMvc.perform(post("/conveyor/calculation")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void controller_ConveyorCalculation_LargeAmountTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000001));
        scoringDataDTO.setTerm(60);
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
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(600000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount("Account");
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorCalculation_SmallTermTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(5);
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
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(60000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount("Account");
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorCalculation_LargeTermTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000000));
        scoringDataDTO.setTerm(61);
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
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(600000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount("Account");
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }
}