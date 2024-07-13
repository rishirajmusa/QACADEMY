package com.geektrust.backend.entitiesTest;

import com.geektrust.backend.entities.Coupons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CouponsTest")
public class CouponsTest {
    private Coupons coupons;

    @BeforeEach
    void setup(){
        //Arrange
        coupons =new Coupons("Coupon 1", 30, 1000,0);
    }
    
    @Test
    @DisplayName("getCouponCode must return Coupon code")
    public void getCouponCode_ShouldReturnCoupon(){
        //Act
        String couponCode = coupons.getCouponCode();
        //Assert
        Assertions.assertEquals("Coupon 1", couponCode);
    }

    @Test
    @DisplayName("getDiscountPercentage must return Discount Percentage")
    public void getDiscountPercentage_ShouldReturnDiscountPercent(){
        //Act
        double discoutPercent = coupons.getDiscountPercentage();
        //Assert
        Assertions.assertEquals(30, discoutPercent);
    }

    @Test
    @DisplayName("getMinimumPurchaseValue value must return Minimum Purchase Value")
    public void getMinimumPurchaseValue_ShouldReturnMinValue(){
        //Act
        double minimumPurchaseValue = coupons.getMinimumPurchaseValue();
        //Assert
        Assertions.assertEquals(1000, minimumPurchaseValue);
    }
}
