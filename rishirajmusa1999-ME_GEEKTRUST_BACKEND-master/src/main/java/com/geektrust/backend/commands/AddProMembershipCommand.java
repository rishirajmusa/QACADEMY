package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.ICartService;


public class AddProMembershipCommand implements Icommand{
    private ICartService cartService;

    public AddProMembershipCommand(ICartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute(List<String> tokens) {
        cartService.addProMembership();
        
    }
    
}
