package com.Neoflex.conveyor.service;


import com.Neoflex.conveyor.model.CreditDTO;
import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.Neoflex.conveyor.model.LoanOfferDTO;
import com.Neoflex.conveyor.model.ScoringDataDTO;

import java.util.ArrayList;
import java.util.List;

public class ConveyorService {

    public List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = new ArrayList<>();
        return offers;
    }

    public CreditDTO getCredit(ScoringDataDTO scoringDataDTO) {
        CreditDTO creditDTO = new CreditDTO();
        return creditDTO;
    }

}
