package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.ICouponService;

public class AddCouponCommand implements Icommand{
    private ICouponService couponService;

    public AddCouponCommand(ICouponService couponService) {
        this.couponService = couponService;
    }

    @Override
    public void execute(List<String> tokens) {
        String couponCode = tokens.get(1);
        couponService.addCoupon(couponCode);
    }
    
}