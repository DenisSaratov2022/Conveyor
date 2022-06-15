package com.Neoflex.conveyor.controller;

import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConveyorControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper objectMapper = JsonMapper.builder().findAndAddModules().build();

    @Test
    void controller_SuccessTest() throws Exception {
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
    void controller_BirthdateTest() throws Exception {
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
    void controller_AbsenceValueTest() throws Exception {
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

}