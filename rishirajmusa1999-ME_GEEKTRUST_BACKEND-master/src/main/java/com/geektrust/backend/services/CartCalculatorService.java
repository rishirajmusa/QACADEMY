package com.geektrust.backend.services;

import java.util.List;
import java.util.Map;
import com.geektrust.backend.dtos.BillDto;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programmes;
import com.geektrust.backend.repositories.IProgrammeRepository;

public class CartCalculatorService implements ICartCalculatorService{
    private IProgrammeRepository programmeRepository;
    private ICartService cartService;
    private ICouponService couponService;

public CartCalculatorService(IProgrammeRepository programmeRepository, ICartService cartService,
            ICouponService couponService ) {
        this.programmeRepository = programmeRepository;
        this.cartService = cartService;
        this.couponService = couponService;
    }

@Override
public BillDto calculateTotal() {
    List<Programmes> listOfPrograms =programmeRepository.findAllUserAddedPrograms();
    if(listOfPrograms.isEmpty()){
       System.err.println("No Programs Added, Please Add Programs");
       return new BillDto();
    }
    double sub_total = cartService.calculateSubTotal(listOfPrograms);

    boolean proMembershipStaus = cartService.hasProMembership();
    Map<String,Double> couponWithDiscount =couponService.applyCouponDiscount(sub_total, listOfPrograms, proMembershipStaus);
    String couponCode = couponWithDiscount.keySet().iterator().next();
    Cart cart = cartService.getCart();
    double total = sub_total - couponWithDiscount.get(couponCode)+ cart.getENROLLMENT_FEE();
    return new BillDto(sub_total, couponWithDiscount, cart.getMEMBERSHIP_DISCOUNT(), cart.getPRO_MEMBERSHIP_FEE(), cart.getENROLLMENT_FEE(), total);
}

    
}
