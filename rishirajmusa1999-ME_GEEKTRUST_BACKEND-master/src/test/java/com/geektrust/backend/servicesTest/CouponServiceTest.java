package com.geektrust.backend.servicesTest;
import com.geektrust.backend.entities.Coupons;
import com.geektrust.backend.repositories.CouponRepository;
import com.geektrust.backend.repositories.ICouponRepository;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CouponService;
import com.geektrust.backend.services.ICouponService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CouponServiceTest")
public class CouponServiceTest {
    private ICouponRepository couponRepository = new CouponRepository();
    private IProgrammeRepository programmeRepository =new ProgrammeRepository();
    private ICouponService couponService = new CouponService(couponRepository, programmeRepository);

    @Test
    @DisplayName("addCoupon method of CouponService should add coupon")
    public void addCoupon_ShouldAddCoupon(){
        //Arrange
        String couponCode = "B4G1";
        //Act
        couponService.addCoupon(couponCode);
        Coupons coupon = couponRepository.findByName(couponCode);
        boolean actual= couponRepository.findAllUserAddedCoupons().contains(coupon);
        //Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("addCoupon method of CouponService should not add invalid coupon")
    public void addCoupon_ShouldNotAdd_GivenWrongCoupon(){
        //Arrange
        String couponCode = "FREE100";
        //Act
        couponService.addCoupon(couponCode);
        Coupons coupon = new Coupons(couponCode, 100.0, 0,0);
        boolean actual= couponRepository.findAllUserAddedCoupons().contains(coupon);
        //Assert
        Assertions.assertEquals(false, actual);
    }
    
    
}
