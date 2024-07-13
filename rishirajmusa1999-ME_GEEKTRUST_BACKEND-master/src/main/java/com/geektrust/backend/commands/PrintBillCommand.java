package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.dtos.BillDto;
import com.geektrust.backend.services.ICartCalculatorService;

public class PrintBillCommand implements Icommand{
private ICartCalculatorService cartCalculatorService;

    public PrintBillCommand(ICartCalculatorService cartCalculatorService) {
    this.cartCalculatorService = cartCalculatorService;
}

    @Override
    public void execute(List<String> tokens) {
        BillDto billDto = cartCalculatorService.calculateTotal();
        System.out.println(billDto);
    }
    
}
