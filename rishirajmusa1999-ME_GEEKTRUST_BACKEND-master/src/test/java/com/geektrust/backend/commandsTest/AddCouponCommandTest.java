package com.geektrust.backend.commandsTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.commands.AddCouponCommand;
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

@DisplayName("AddCouponCommandTest")
public class AddCouponCommandTest {
    private Map<String,Coupons> couponsMap = new HashMap<String,Coupons>(){
        {
            put("DEAL_G20",new Coupons("DEAL_G20", 20, 10000,0));
        }
    };
    private ICouponRepository couponRepository  = new CouponRepository(couponsMap);
    private IProgrammeRepository programmeRepository =new ProgrammeRepository();
    private ICouponService couponService = new CouponService(couponRepository,programmeRepository);
    private AddCouponCommand addCouponCommand = new AddCouponCommand(couponService);

    @Test
    @DisplayName("execute of AddCouponCommand Should Save the Coupon")
    public void execute_ShouldSaveCoupon(){
        //Arrange
        List<String> tokens = List.of("APPLY_COUPON", "DEAL_G20");
        //Act
        addCouponCommand.execute(tokens);
        String actual =couponRepository.findAllUserAddedCoupons().get(0).getCouponCode();
        //Assert
        Assertions.assertEquals("DEAL_G20", actual);
    }
    
}
