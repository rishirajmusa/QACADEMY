package com.geektrust.backend.servicesTest;
import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.dtos.BillDto;
import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.Coupons;
import com.geektrust.backend.entities.Programmes;
import com.geektrust.backend.repositories.CouponRepository;
import com.geektrust.backend.repositories.ICouponRepository;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CartCalculatorService;
import com.geektrust.backend.services.CartService;
import com.geektrust.backend.services.CouponService;
import com.geektrust.backend.services.ICartCalculatorService;
import com.geektrust.backend.services.ICartService;
import com.geektrust.backend.services.ICouponService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CartCalculatorServiceTest")
public class CartCalculatorServiceTest {
    private ICouponRepository couponRepository =new CouponRepository();
    private IProgrammeRepository programmeRepository=new ProgrammeRepository();
    private ICartService cartService =new CartService(programmeRepository);
    private ICouponService couponService =new CouponService(couponRepository, programmeRepository);
    private Programmes programmes1,programmes2,programmes3;
    private ICartCalculatorService cartCalculatorService =
    new CartCalculatorService(programmeRepository, cartService, couponService);

    @BeforeEach
    void setup(){
        Coupons coupons1 = new Coupons("B4G1", 0.0, 0.0,4);
        Coupons coupons2= new Coupons("DEAL_G5", 5.0, 0.0,2);
        Coupons coupons3= new Coupons("DEAL_G20", 20.0, 10000.0,0);
        couponRepository.save(coupons1);
        couponRepository.save(coupons2);
        couponRepository.save(coupons3);
        programmes1= new Programmes(3000, Category.CERTIFICATION);
        programmes2= new Programmes(2500, Category.DIPLOMA);
        programmes3= new Programmes(5000, Category.DEGREE);
    }

    @Test
    @DisplayName("calculateTotal method of CartCalculatorService should calculate Total")
    public void calculateTotal_ShouldCalculateTotal(){
        //Arramge
        programmeRepository.save(programmes1);
        programmeRepository.save(programmes2);
        programmeRepository.save(programmes3);
        Map<String, Double> couponWithDiscount = new HashMap<String,Double>() {
            {put("DEAL_G20", 2100.0);}
        };
        BillDto expected = new BillDto(10500, couponWithDiscount, 0.0, 0.0, 0.0, 8400);
        //Act
        BillDto actual = cartCalculatorService.calculateTotal();
        //Assert
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @DisplayName("calculateTotal method of CartCalculatorService should Return Error Message with Empty Bill if no Programes added")
    public void calculateTotal_ShouldReturnError_GivenNoProgramsAdded(){
        //Arramge
        Map<String, Double> couponWithDiscount =new HashMap<>();
        BillDto expected = new BillDto(0, couponWithDiscount, 0.0, 0.0, 0.0, 0);
        //Act
        BillDto actual = cartCalculatorService.calculateTotal();
        //Assert
        Assertions.assertEquals(expected.toString(), actual.toString());
    }
    
}
