package com.geektrust.backend.dtos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BillDto {
    private final double sub_total;
    private final Map<String, Double> couponWithDiscount;
    private final double proMemberDiscount;
    private final double proMemberFee;
    private final double enrollmentFee;
    private final double total;
    
    public BillDto() {
        this.sub_total = 0;
        this.couponWithDiscount = new HashMap<>();
        this.proMemberDiscount = 0;
        this.proMemberFee = 0;
        this.enrollmentFee = 0;
        this.total = 0;
    }


    public BillDto(double sub_total,Map<String,Double> couponWithDiscount, double proMemberDiscount, double proMemberFee,
    double enrollmentFee, double total) {
        this.sub_total = sub_total;
        this.couponWithDiscount = couponWithDiscount;
        this.proMemberDiscount = proMemberDiscount;
        this.proMemberFee = proMemberFee;
        this.enrollmentFee = enrollmentFee;
        this.total = total;
    }


 private String couponCode(){
    return couponWithDiscount.keySet().iterator().next();
 }
 private double couponDiscount(){
    return couponWithDiscount.get(couponCode());
 }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (sub_total == 0) {
            return "TOTAL 0.00";
        }
        return "SUB_TOTAL " + df.format(sub_total) + "\n" +
               "COUPON_DISCOUNT " + couponCode() + " " + df.format(couponDiscount()) + "\n" +
               "TOTAL_PRO_DISCOUNT " + df.format(proMemberDiscount) + "\n" +
               "PRO_MEMBERSHIP_FEE " + df.format(proMemberFee) + "\n" +
               "ENROLLMENT_FEE " + df.format(enrollmentFee) + "\n" +
               "TOTAL " + df.format(total);
    }
}