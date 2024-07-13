package com.geektrust.backend.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Coupons;
import com.geektrust.backend.entities.ProMembership;
import com.geektrust.backend.entities.Programmes;
import com.geektrust.backend.repositories.ICouponRepository;
import com.geektrust.backend.repositories.IProgrammeRepository;

public class CouponService implements ICouponService {
    private final ICouponRepository couponRepository;
    private final IProgrammeRepository programmeRepository;
    private final ProMembership proMembership = new ProMembership();
    private static final int PERCENTAGE_FACTOR = 100;
    public static final String COUPON_B4G1 = "B4G1";



    public CouponService(ICouponRepository couponRepository, IProgrammeRepository programmeRepository) {
    this.couponRepository = couponRepository;
    this.programmeRepository = programmeRepository;
}

    @Override
    public void addCoupon(String couponCode) {
        if(couponRepository.checkIfExist(couponCode)){
        Coupons coupons = couponRepository.findByName(couponCode);
        couponRepository.save(coupons);
        }
    }

    @Override
    public Map<String, Double> applyCouponDiscount(double total, List<Programmes> listOfPrograms, boolean proMembershipStaus) {
        List<Coupons> coupons = couponRepository.findAllUserAddedCoupons();
    int cartSize = programmeRepository.getCartSize();
    double totalDiscount =0, maxDiscount=0;
    String usedCoupon="";
    if(cartSize >= 4){
        maxDiscount = calculateB4G1Discount(listOfPrograms,proMembershipStaus);
        usedCoupon = COUPON_B4G1;
    }else{
        for (Coupons coupon : coupons) {
            if( total>=coupon.getMinimumPurchaseValue() && cartSize >=coupon.getCartSize()){
                totalDiscount = total*coupon.getDiscountPercentage()/PERCENTAGE_FACTOR;
            }
            if(totalDiscount>maxDiscount){
                maxDiscount=totalDiscount;
                usedCoupon = coupon.getCouponCode();
            }
        }
      
    }
    if(usedCoupon.isEmpty()){
        return new HashMap<String,Double>(Map.of("NONE", 0.0));
    }
    Map<String,Double> result = new HashMap<>(Map.of(usedCoupon, maxDiscount));
    return result;
    }

    private double calculateB4G1Discount(List<Programmes> listOfPrograms, boolean proMembershipStaus){
        listOfPrograms.sort(Comparator.comparingDouble(Programmes::getPrice));
        double totalDiscount =0;
        double proDiscount =0;
        double programPrice = listOfPrograms.get(0).getPrice();
        Programmes programme = listOfPrograms.get(0);
        if(proMembershipStaus){
           proDiscount = proMembership.getDiscountPercentage(programme);
        }
       
        totalDiscount +=programPrice*proDiscount/PERCENTAGE_FACTOR;
        return programPrice-totalDiscount;
        
    }
    
}
