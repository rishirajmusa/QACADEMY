package com.geektrust.backend.entitiesTest;

import com.geektrust.backend.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("categoryTest")
public class categoryTest {
    private Category catagory;

    @Test
    @DisplayName("getCategoryByName Should Return Catagory with name")
    public void getCategoryByName_ShouldReturnCatagory(){
        //Arrange
        String expected = "CERTIFICATION";
        //Act
        catagory = Category.getCategoryByName("CERTIFICATION");
        //Assert
        Assertions.assertEquals(expected, catagory.toString());
    }


    @Test
    @DisplayName("Category Should Return dummy catagory If not an Actual category")
    public void category_ShouldReturnDumForWrongCatagory(){
        //Arrange
        String expected = "NOT_FOUND";
        //Act
        catagory = Category.getCategoryByName("asddf");
        //Assert
        Assertions.assertEquals(expected, catagory.toString());
    }
}
