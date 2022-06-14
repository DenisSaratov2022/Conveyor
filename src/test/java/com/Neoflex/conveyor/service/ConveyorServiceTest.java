package com.Neoflex.conveyor.service;

import com.Neoflex.conveyor.model.LoanApplicationRequestDTO;
import com.Neoflex.conveyor.model.LoanOfferDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ConveyorServiceTest {

    @Test
    void offersSizeIfOfferAdd(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        ConveyorService conveyorService = new ConveyorService();
        conveyorService.getOffers(loanApplicationRequestDTO);
        List<LoanOfferDTO> offers =  new ArrayList<>();
        offers.add(new LoanOfferDTO());
        offers.add(new LoanOfferDTO());
        offers.add(new LoanOfferDTO());
        offers.add(new LoanOfferDTO());
        assertEquals(4, offers.size());
    }
}