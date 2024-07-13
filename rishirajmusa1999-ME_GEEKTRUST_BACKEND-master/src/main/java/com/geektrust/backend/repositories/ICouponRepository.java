package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Map;
import com.geektrust.backend.entities.Coupons;

public interface ICouponRepository extends CRUDRepository<Coupons>{
    public boolean checkIfExist(String couponCode);
    public Coupons findByName(String couponCode);
    public List<Coupons> findAllUserAddedCoupons();
    public Map<String, Coupons> findAll();
}
