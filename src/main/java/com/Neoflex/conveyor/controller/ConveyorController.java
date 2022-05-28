package com.Neoflex.conveyor.controller;

import com.Neoflex.conveyor.model.CreditDTO;
import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.Neoflex.conveyor.model.LoanOfferDTO;
import com.Neoflex.conveyor.model.ScoringDataDTO;
import com.Neoflex.conveyor.service.ConveyorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConveyorController {
    private final ConveyorService conveyorService;

    @PostMapping("/conveyor/offers")
    public List<LoanOfferDTO> conveyorOffers (@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO){
        log.info("conveyorOffers method started with params: amount={}, term={}, firstName={}, lastName={}, " +
                        "middleName={}, email={}, birthdate={}, passportSeries={}, passportNumber={}",
                loanApplicationRequestDTO.getAmount(), loanApplicationRequestDTO.getTerm(), loanApplicationRequestDTO.getFirstName(),
                loanApplicationRequestDTO.getLastName(), loanApplicationRequestDTO.getMiddleName(), loanApplicationRequestDTO.getEmail(),
                loanApplicationRequestDTO.getBirthdate(), loanApplicationRequestDTO.getPassportSeries(), loanApplicationRequestDTO.getPassportNumber());
    return conveyorService.getOffers(loanApplicationRequestDTO);
    }

    @PostMapping("/conveyor/calculation")
    public CreditDTO conveyorCalculation (@RequestBody ScoringDataDTO scoringDataDTO) {
        log.info("conveyorCalculation method started with params: amount={}, term={}, firstName={}, lastName={}, " +
        "middleName={}, gender={}, birthdate={}, passportSeries={}, passportNumber={}, passportIssueDate={}, passportIssueBranch={}, maritalStatus={}, " +
                "dependentAmount={}, employment={}, account={}, isInsuranceEnabled={}, isSalaryClient={}",
                scoringDataDTO.getAmount(), scoringDataDTO.getTerm(), scoringDataDTO.getFirstName(), scoringDataDTO.getLastName(), scoringDataDTO.getMiddleName(),
                scoringDataDTO.getGender(), scoringDataDTO.getBirthdate(), scoringDataDTO.getPassportSeries(), scoringDataDTO.getPassportNumber(),
                scoringDataDTO.getPassportIssueDate(), scoringDataDTO.getPassportIssueBranch(), scoringDataDTO.getMaritalStatus(), scoringDataDTO.getDependentAmount(),
                scoringDataDTO.getEmployment(), scoringDataDTO.getAccount(), scoringDataDTO.getIsInsuranceEnabled(), scoringDataDTO.getIsSalaryClient());
        return conveyorService.getCredit(scoringDataDTO);
    }
}
