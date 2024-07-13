package com.geektrust.backend.entitiesTest;
import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.Programmes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("CartTest")
public class CartTest {
    private Cart cart;
    private List<Programmes> listOfPrograms;

    @BeforeEach
    void setup(){
        //Arrange
        Programmes programmes =new Programmes(5000, Category.CERTIFICATION);
        Programmes programmes2 =new Programmes(2500, Category.DIPLOMA);
        listOfPrograms = List.of(programmes, programmes2);
        cart =new Cart(listOfPrograms, 123, 456, 789);
    }

    @Test
    @DisplayName("getProgrammes should return the list of programs")
    public void getProgrammes_ShouldReturnPrograms(){
        //Act
        List<Programmes> actual =  cart.getProgrammes();
        //Assert
        Assertions.assertEquals(listOfPrograms, actual);
    }

    @Test
    @DisplayName("getPRO_MEMBERSHIP_FEE should return the Membership fee")
    public void getPRO_MEMBERSHIP_FEE_ShouldReturnMemberFee(){
        //Act
        double actual =  cart.getPRO_MEMBERSHIP_FEE();
        //Assert
        Assertions.assertEquals(123, actual);
    }

    @Test
    @DisplayName("getENROLLMENT_FEE should return the Enrollment fee")
    public void getENROLLMENT_FEE_ShouldReturnEnrollFee(){
        //Act
        double actual =  cart.getENROLLMENT_FEE();
        //Assert
        Assertions.assertEquals(789, actual);
    }

    @Test
    @DisplayName("getMEMBERSHIP_DISCOUNT should return the Membership Discount")
    public void getMEMBERSHIP_DISCOUNT_ShouldReturnDiscount(){
        //Act
        double actual =  cart.getMEMBERSHIP_DISCOUNT();
        //Assert
        Assertions.assertEquals(456, actual);
    }
    
}
