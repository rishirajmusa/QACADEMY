package com.geektrust.backend.entities;

public class Coupons {

    private final String couponCode;
    private final double discountPercentage;
    private final double minimumPurchaseValue;
    private final int cartSize;

 

    public Coupons(String couponCode, double discountPercentage, double minimumPurchaseValue,
            int cartSize) {
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.minimumPurchaseValue = minimumPurchaseValue;
        this.cartSize = cartSize;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public Double getMinimumPurchaseValue() {
        return minimumPurchaseValue;
    }

    public int getCartSize() {
        return cartSize;
    }

    @Override
    public String toString() {
        if(!getCouponCode().isEmpty()){
            return "Coupons [couponCode=" + couponCode + ", discountPercentage=" + discountPercentage
            + ", minimumPurchaseValue=" + minimumPurchaseValue + ", cartSize=" + cartSize + "]";
        }
        return "Coupon Does not Exist";
    }



  
}
