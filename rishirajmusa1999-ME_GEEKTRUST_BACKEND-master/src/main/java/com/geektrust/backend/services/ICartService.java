package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Cart;
import com.geektrust.backend.entities.Programmes;

public interface ICartService {
    public void addProgrammesToCart(String programName, String programCount);
    public void addProMembership();
    public boolean hasProMembership();
    public Cart getCart();
    public double calculateSubTotal(List<Programmes> listOfPrograms);
}
