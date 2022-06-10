package com.Neoflex.conveyor.controller;

import com.Neoflex.conveyor.model.CreditDTO;
import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.Neoflex.conveyor.model.LoanOfferDTO;
import com.Neoflex.conveyor.model.ScoringDataDTO;
import com.Neoflex.conveyor.service.ConveyorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ConveyorController {
    private final ConveyorService conveyorService;

    @PostMapping("/conveyor/offers")
    public List<LoanOfferDTO> conveyorOffers (@RequestBody @Valid LoanApplicationRequestDTO loanApplicationRequestDTO){
        log.info("conveyorOffers method started with params: "+loanApplicationRequestDTO.toString());
    return conveyorService.getOffers(loanApplicationRequestDTO);
    }

    @PostMapping("/conveyor/calculation")
    public CreditDTO conveyorCalculation (@RequestBody ScoringDataDTO scoringDataDTO) {
        log.info("conveyorCalculation method started with params: " + scoringDataDTO.toString());
        return conveyorService.getCredit(scoringDataDTO);
    }
    //
}
