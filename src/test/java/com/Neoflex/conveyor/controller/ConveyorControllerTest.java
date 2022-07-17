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
    void controller_ConveyorOffers_SmallAmountTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(1000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(28));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_LargeAmountTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000001));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(28));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_SmallTermTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(5);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(28));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_VeryBigTermTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(61);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(28));
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
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_AbsenceLastNameTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName(null);
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6662");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_AbsenceMiddleNameTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName(null);
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
    void controller_ConveyorOffers_PassportSeriesSmallTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("666");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_PassportSeriesVeryBigTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("66666");
        loanApplicationRequestDTO.setPassportNumber("454545");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_PassportNumberSmallTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6666");
        loanApplicationRequestDTO.setPassportNumber("45454");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorOffers_PassportNumberVeryBigTest() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(10000));
        loanApplicationRequestDTO.setTerm(6);
        loanApplicationRequestDTO.setFirstName("firstName");
        loanApplicationRequestDTO.setLastName("lastName");
        loanApplicationRequestDTO.setMiddleName("middleName");
        loanApplicationRequestDTO.setEmail("qwerty@gmail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.now().minusYears(25));
        loanApplicationRequestDTO.setPassportSeries("6666");
        loanApplicationRequestDTO.setPassportNumber("4545455");
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorCalculation_SuccessTest() throws Exception {
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

    @Test
    void controller_ConveyorCalculation_AbsenceFirstNameTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(5);
        scoringDataDTO.setFirstName(null);
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
    void controller_ConveyorCalculation_AbsenceLastNameTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName(null);
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
    void controller_ConveyorCalculation_AbsenceMiddleNameTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("firstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName(null);
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
    void controller_ConveyorCalculation_BirthdateFromTheFutureTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().plusYears(2));
        scoringDataDTO.setPassportSeries("6662");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(20));
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
    void controller_ConveyorCalculation_PassportSeriesSmallTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("662");
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
    void controller_ConveyorCalculation_PassportSeriesVeryBigTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("66266");
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
    void controller_ConveyorCalculation_PassportNumberSmallTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("66625");
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
    void controller_ConveyorCalculation_PassportNumberVeryBigTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("6662566");
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
    void controller_ConveyorCalculation_PassportIssueDateFromTheFutureTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6662");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().plusYears(15));
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
    void controller_ConveyorCalculation_PassportIssueDateVeryOldTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6662");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(60));
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
    void controller_ConveyorCalculation_PassportIssueBranchSmallTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("P");
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
    void controller_ConveyorCalculation_AbsencePassportIssueBranchTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch(null);
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
    void controller_ConveyorCalculation_NegativeDependentAmountTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(-1);
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
    void controller_ConveyorCalculation_SmallINNTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "12345678911", BigDecimal.valueOf(60000), Position.TOP_MANAGER, 46, 6));
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
    void controller_ConveyorCalculation_VeryBigINNTest() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "1234567891012", BigDecimal.valueOf(60000), Position.TOP_MANAGER, 46, 6));
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
    void controller_ConveyorCalculation_NegativeSalary() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(-20000), Position.TOP_MANAGER, 46, 6));
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
    void controller_ConveyorCalculation_NegativeWorkExperienceTotal() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(20000), Position.TOP_MANAGER, -46, 6));
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
    void controller_ConveyorCalculation_NegativeWorkExperienceCurrent() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(20000), Position.TOP_MANAGER, 46, -6));
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
    void controller_ConveyorCalculation_AbsenceAccount() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(60000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount(null);
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void controller_ConveyorCalculation_SmallAccount() throws Exception {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("FirstName");
        scoringDataDTO.setLastName("lastName");
        scoringDataDTO.setMiddleName("middleName");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.now().minusYears(35));
        scoringDataDTO.setPassportSeries("6626");
        scoringDataDTO.setPassportNumber("666245");
        scoringDataDTO.setPassportIssueDate(LocalDate.now().minusYears(15));
        scoringDataDTO.setPassportIssueBranch("PassportIssueBranch");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(0);
        scoringDataDTO.setEmployment(new EmploymentDTO(EmploymentStatus.EMPLOYED, "123456789112", BigDecimal.valueOf(60000), Position.TOP_MANAGER, 46, 6));
        scoringDataDTO.setAccount("A");
        scoringDataDTO.setIsInsuranceEnabled(true);
        scoringDataDTO.setIsSalaryClient(true);
        mockMvc.perform(post("/conveyor/calculation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scoringDataDTO)))
                .andExpect(status().isBadRequest());
    }
}