package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.ICartService;

public class AddProgrammesCommand implements Icommand{
    private ICartService cartService;

    public AddProgrammesCommand(ICartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute(List<String> tokens) {
       String programName = tokens.get(1);
       String programCount = tokens.get(2);
       cartService.addProgrammesToCart(programName, programCount);
    }
    
}