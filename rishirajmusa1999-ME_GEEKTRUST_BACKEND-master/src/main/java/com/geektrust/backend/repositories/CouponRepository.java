package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Coupons;

public class CouponRepository implements ICouponRepository{
private final Map<String,Coupons> couponsMap;
private final List<Coupons> couponsList =new ArrayList<>();

public CouponRepository() {
    couponsMap = new HashMap<>();
    initializeCoupons();
}

public CouponRepository(Map<String, Coupons> couponsMap) {
    this.couponsMap = couponsMap;
}

    @Override
    public void save(Coupons entity) {
       if(!couponsList.contains(entity)){
        couponsList.add(entity);
       }
    }
    
    @Override
    public List<Coupons> findAllUserAddedCoupons() {
        return couponsList;
    }

    @Override
    public Map<String, Coupons> findAll() {
        return couponsMap;
    }
    
    @Override
    public boolean checkIfExist(String couponCode) {
        return couponsMap.containsKey(couponCode);
    }

    @Override
    public Coupons findByName(String couponCode) {
        if(checkIfExist(couponCode)){
            return couponsMap.get(couponCode);
        }
        return new Coupons("", 0, 0,0);
        
    }

    private void initializeCoupons(){
        couponsMap.put("B4G1", new Coupons("B4G1", 0.0, 0.0,4));
        couponsMap.put("DEAL_G20", new Coupons("DEAL_G20", 20.0, 10000.0,0));
        couponsMap.put("DEAL_G5", new Coupons("DEAL_G5", 5.0, 0.0,2));
    }

 
    
}
