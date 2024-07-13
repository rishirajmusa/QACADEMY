package com.geektrust.backend.entitiesTest;
import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.ProMembership;
import com.geektrust.backend.entities.Programmes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProMembershipTest")
public class ProMembershipTest {
    
    private ProMembership proMembership = new ProMembership();
    

    @Test
    @DisplayName("getDiscountPercentage method of ProMembership Should Return the discount of respective Category")
    public void getCertificationDiscount_ShouldReturnDiscount(){
        //Arrange
        Programmes programme = new Programmes(5000, Category.CERTIFICATION);
        //Act
        double certificationDiscountPercentage = proMembership.getDiscountPercentage(programme);
        //Assert
        Assertions.assertEquals(2.0, certificationDiscountPercentage);
    }

    @Test
    @DisplayName("getDiscountPercentage method of ProMembership Should Return the diploma discont")
    public void getDiplomaDiscount_ShouldReturnDiscount(){
         //Arrange
         Programmes programme = new Programmes(5000, Category.DIPLOMA);
        //Act
        double diplomaDiscountPercentage = proMembership.getDiscountPercentage(programme);
        //Assert
        Assertions.assertEquals(1.0, diplomaDiscountPercentage);
    }

    @Test
    @DisplayName("getDiscountPercentage method of ProMembership Should Return the degree discont")
    public void getDegreeDiscount_ShouldReturnDiscount(){
        //Arrange
        Programmes programme = new Programmes(5000, Category.DEGREE);
        //Act
        double degreeDiscountPercentage = proMembership.getDiscountPercentage(programme);
        //Assert
        Assertions.assertEquals(3.0, degreeDiscountPercentage);
    }
}
