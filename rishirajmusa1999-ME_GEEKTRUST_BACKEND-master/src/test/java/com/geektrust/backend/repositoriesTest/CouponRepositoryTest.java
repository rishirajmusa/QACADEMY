package com.geektrust.backend.repositoriesTest;
import com.geektrust.backend.entities.Coupons;
import com.geektrust.backend.repositories.CouponRepository;
import com.geektrust.backend.repositories.ICouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CouponRepositoryTest")
public class CouponRepositoryTest {
    private ICouponRepository couponRepository =new CouponRepository();
    private Coupons coupons, coupons2;


    @BeforeEach
    void setup(){
        //Arrange
        coupons =new Coupons("Wrong Coupon", 5, 100,0);
        coupons2 =new Coupons("B4G1", 0.0, 0.0,4);
        couponRepository.save(coupons);
        couponRepository.save(coupons2);
    }

    @Test
    @DisplayName("save method of CouponRepository should save the coupon")
    public void save_ShouldSaveCoupon(){
        //Act
        boolean actual = couponRepository.findAllUserAddedCoupons().contains(coupons);
        //Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("checkIfExist method of CouponRepository should return false if coupon not valid")
    public void checkIfExist_ShouldReturnFalse_GivenFalseCoupon(){
        //Act
        boolean actual = couponRepository.checkIfExist(coupons.getCouponCode());
        //Assert
        Assertions.assertEquals(false, actual);
    }

    @Test
    @DisplayName("checkIfExist method of CouponRepository should return true if coupon is valid")
    public void checkIfExist_ShouldReturnTrue(){
        //Act
        boolean actual = couponRepository.checkIfExist(coupons2.getCouponCode());
        //Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("findByName method of CouponRepository should return coupon by name if exist")
    public void findByName_ShouldReturnCoupon(){
        //Act
        Coupons actual  = couponRepository.findByName(coupons2.getCouponCode());
        //Assert
        Assertions.assertEquals(coupons2.toString(), actual.toString());
    }

    @Test
    @DisplayName("findByName method of CouponRepository should return not found message is dosen't exist")
    public void findByName_ShouldSaveCoupon(){
        String expected = "Coupon Does not Exist";
        //Act
        Coupons actual  = couponRepository.findByName(coupons.getCouponCode());
        //Assert
        Assertions.assertEquals(expected, actual.toString());
    }

    
}
