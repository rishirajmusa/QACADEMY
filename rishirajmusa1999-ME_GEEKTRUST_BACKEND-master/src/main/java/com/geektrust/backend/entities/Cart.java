package com.geektrust.backend.entities;

import java.util.List;

public class Cart {
    private final List<Programmes> listOfPrograms;
    private final double PRO_MEMBERSHIP_FEE;
    private final double MEMBERSHIP_DISCOUNT;
    private final double ENROLLMENT_FEE;

    public Cart(List<Programmes> listOfPrograms, double pRO_MEMBERSHIP_FEE, double mEMBERSHIP_DISCOUNT,
            double eNROLLMENT_FEE) {
        this.listOfPrograms = listOfPrograms;
        PRO_MEMBERSHIP_FEE = pRO_MEMBERSHIP_FEE;
        MEMBERSHIP_DISCOUNT = mEMBERSHIP_DISCOUNT;
        ENROLLMENT_FEE = eNROLLMENT_FEE;
    }
    public List<Programmes> getProgrammes() {
        return listOfPrograms;
    }
    public double getPRO_MEMBERSHIP_FEE() {
        return PRO_MEMBERSHIP_FEE;
    }
    public double getENROLLMENT_FEE() {
        return ENROLLMENT_FEE;
    }
    public double getMEMBERSHIP_DISCOUNT() {
        return MEMBERSHIP_DISCOUNT;
    }
    @Override
    public String toString() {
        return "Cart [ENROLLMENT_FEE=" + ENROLLMENT_FEE + ", MEMBERSHIP_DISCOUNT="
                + MEMBERSHIP_DISCOUNT + ", PRO_MEMBERSHIP_FEE=" + PRO_MEMBERSHIP_FEE
                + ", programmes=" + listOfPrograms + "]";
    }

}
 
