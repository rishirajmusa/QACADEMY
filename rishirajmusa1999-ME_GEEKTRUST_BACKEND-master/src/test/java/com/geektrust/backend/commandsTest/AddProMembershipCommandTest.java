package com.geektrust.backend.commandsTest;

import java.util.List;
import com.geektrust.backend.commands.AddProMembershipCommand;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CartService;
import com.geektrust.backend.services.ICartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AddProMembershipCommandTest")
public class AddProMembershipCommandTest {
    private IProgrammeRepository programmeRepository = new ProgrammeRepository();
    private ICartService cartService = new CartService(programmeRepository);
    private AddProMembershipCommand addProMembershipCommand =new AddProMembershipCommand(cartService);

    @Test
    @DisplayName("execute method of AddProMembershipCommand Should Add Membership")
    public void execute_ShouldAddMembership(){
        //Arrange
        List<String> tokens =List.of("ADD_PRO_MEMBERSHIP");
        //Act
        addProMembershipCommand.execute(tokens);
        boolean actual = cartService.hasProMembership();
        //Assert
        Assertions.assertEquals(true, actual);
    }
    
}
