package com.geektrust.backend.commandsTest;

import java.util.List;
import com.geektrust.backend.commands.AddProgrammesCommand;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CartService;
import com.geektrust.backend.services.ICartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AddProgrammeCommandTest")
public class AddProgrammeCommandTest {
    
    private IProgrammeRepository programmeRepository = new ProgrammeRepository();
    private ICartService cartService = new CartService(programmeRepository);
    private AddProgrammesCommand addProgrammesCommand = new AddProgrammesCommand(cartService);

    @Test
    @DisplayName("execute method of AddProgrammesCommand Should Add Program")
    public void execute_ShouldAddProgram(){
        //Arrange
        List<String> tokens = List.of("ADD_PROGRAMME", "CERTIFICATION", "1");
        //Act
        addProgrammesCommand.execute(tokens);
        //Assert
        Assertions.assertEquals(true, programmeRepository.checkIfExist("CERTIFICATION"));

    }
}
