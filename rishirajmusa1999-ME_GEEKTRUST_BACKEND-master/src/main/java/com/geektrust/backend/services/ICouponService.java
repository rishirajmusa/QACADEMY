package com.geektrust.backend.services;

import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Programmes;

public interface ICouponService {
    public void addCoupon(String couponId);
    public Map<String,Double> applyCouponDiscount(double total, List<Programmes> listOfPrograms,boolean proMembershipStaus);
    
}