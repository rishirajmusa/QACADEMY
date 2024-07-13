package com.geektrust.backend.servicesTest;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CartService;
import com.geektrust.backend.services.ICartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CartServiceTest")
public class CartServiceTest {
        private IProgrammeRepository programmeRepository = new ProgrammeRepository();
        private ICartService cartService = new CartService(programmeRepository);

    @Test
    @DisplayName("addProgrammesToCart method of cartService should add programs to cart")
    public void addProgrammesToCart_ShouldAddProgrammesToCart(){
        //Arrange
        cartService.addProgrammesToCart("CERTIFICATION", "10");
        //Act
        int actual = programmeRepository.getCartSize();
        //Assert
        Assertions.assertEquals(10,actual);
    }

    @Test
    @DisplayName("addProgrammesToCart method of cartService should not add Invalid programs")
    public void addProgrammesToCart_ShouldReturnInvalid_GivenInvalidProgram(){
        //Arrange
        cartService.addProgrammesToCart("WrongName", "10");
        //Act
        int actual = programmeRepository.getCartSize();
        //Assert
        Assertions.assertEquals(0,actual);
    }

    @Test
    @DisplayName("addProMembership method of cartService should Add ProMembership")
    public void addProMembership_ShouldAddMembership(){
        //Arrange
        cartService.addProMembership();
        //Act
        boolean actual = cartService.hasProMembership();
        //Assert
        Assertions.assertEquals(true,actual);
    }


}
