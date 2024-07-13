package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.ProMembership;
import com.geektrust.backend.entities.Programmes;
import com.geektrust.backend.repositories.IProgrammeRepository;

public class CartService implements ICartService{
    private final IProgrammeRepository programmeRepository;
    private boolean proMembership = false;
    private static final int PERCENTAGE_FACTOR = 100;
    private static final double MIN_SUBTOTAL_FOR_DISCOUNT = 6666;
    private static final double ENROLLMENT_FEE = 500;
    private static final double MEMBERSHIP_FEE = 200;
    private Cart cart;

    public CartService(IProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    @Override
    public void addProgrammesToCart(String programName, String programCount) {
        try {
            int numberOfProgrammes = Integer.parseInt(programCount);
        if(!programmeRepository.checkIfExist(programName)){
            return;
        }
        double programPrice = programmeRepository.getProgramPricesByName(programName);
        Category category= Category.getCategoryByName(programName);
        if(category.equals(Category.NOT_FOUND)){
            System.err.println("Invalid Category");
            return;
        }
        for(int i=0;i< numberOfProgrammes;i++){
            programmeRepository.save(new Programmes(programPrice, category));
        }
        } catch (NumberFormatException e) {
            System.err.println("Invalid programCount: "+programCount);
        }
        
    }

    @Override
    public double calculateSubTotal(List<Programmes> listOfPrograms) {
        double sub_total = listOfPrograms.stream().mapToDouble(Programmes::getPrice).sum();
        double proMembershipDiscount = calculateProMemberShipDiscount(listOfPrograms);
        sub_total -= proMembershipDiscount;
        double membershipFee =0,enrollmentFee=0;
        if(hasProMembership()){ 
            sub_total += MEMBERSHIP_FEE; 
            membershipFee = MEMBERSHIP_FEE; 
        } 
        if(sub_total < MIN_SUBTOTAL_FOR_DISCOUNT){ 
            enrollmentFee = ENROLLMENT_FEE; 
        }
        Cart cart = new Cart(listOfPrograms, membershipFee, proMembershipDiscount, enrollmentFee);
        setCart(cart);
        return sub_total;
    }

    private double calculateProMemberShipDiscount(List<Programmes> listOfPrograms) {
        if(!hasProMembership()){
            return 0;
        }
        ProMembership proMembership = new ProMembership();
        double totalProDiscount =0;
        for(Programmes programmes: listOfPrograms){
        double programPrice = programmes.getPrice();
        double proDiscountRate = proMembership.getDiscountPercentage(programmes);
        totalProDiscount +=programPrice*proDiscountRate/PERCENTAGE_FACTOR;
        }
        return totalProDiscount;
    }
    @Override
    public Cart getCart() {
        return cart;
    }
    
    @Override
    public void addProMembership() {
        if(!proMembership){
       this.proMembership = true;
        }
    }

    @Override
    public boolean hasProMembership() {
        return proMembership;
    }


    private void setCart(Cart cart) {
        this.cart = cart;
    }

}
