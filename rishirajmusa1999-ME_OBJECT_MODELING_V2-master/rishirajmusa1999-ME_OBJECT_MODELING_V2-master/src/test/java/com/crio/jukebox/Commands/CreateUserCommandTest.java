package com.crio.jukebox.Commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@DisplayName("Create JukeBox User")
@ExtendWith(MockitoExtension.class)
public class CreateUserCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IUserService userServicemock;
    @InjectMocks
    CreateUserCommand createUserCommand;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Craete User Command should return a user")
    public void createUserCommand_ShouldReturnAUser(){
        List<String> tokens = List.of("CREATE-USER", "Kiran");
        String expectedOutPut = "1 Kiran";
        User u1=new User("1","Kiran");
        when(userServicemock.create(anyString())).thenReturn(u1);
         createUserCommand.execute(tokens);
        Assertions.assertEquals(expectedOutPut, outputStreamCaptor.toString().trim()); 
        verify(userServicemock,times(1)).create(anyString());
    
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
}
