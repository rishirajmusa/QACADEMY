package com.geektrust.backend.entitiesTest;

import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.Programmes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProgrammesTest")
public class ProgrammesTest {
    private Programmes programmes;

    @BeforeEach
    void setup(){
        //Arrange
        programmes = new Programmes(5000, Category.CERTIFICATION);
    }

    @Test
    @DisplayName("getPrice method of Programme should return price")
    public void getPrice_ShouldReturnPrice(){
        //Act
        double priceOfProgram = programmes.getPrice();
        //Assert
        Assertions.assertEquals(5000.0, priceOfProgram);
    }
    @Test
    @DisplayName("getCategory method of Programme should return category")
    public void getCategory_ShouldReturnPrice(){
        //Act
        Category category = programmes.getCategories();
        //Assert
        Assertions.assertEquals("CERTIFICATION", category.toString());
    }
    
}
