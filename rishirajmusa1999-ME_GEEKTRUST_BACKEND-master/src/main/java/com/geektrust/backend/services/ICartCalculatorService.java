package com.geektrust.backend.services;

import com.geektrust.backend.dtos.BillDto;

public interface ICartCalculatorService {
    public BillDto calculateTotal();
}
