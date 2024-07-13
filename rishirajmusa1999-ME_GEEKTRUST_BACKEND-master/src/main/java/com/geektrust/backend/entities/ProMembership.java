package com.geektrust.backend.entities;

import java.util.HashMap;
import java.util.Map;

public class ProMembership {
    private Map<Category, Double> discountPercentages;

    public ProMembership() {
        discountPercentages = new HashMap<>();
        discountPercentages.put(Category.DIPLOMA, 1.0); // 10% discount
        discountPercentages.put(Category.CERTIFICATION, 2.0); // 20% discount
        discountPercentages.put(Category.DEGREE, 3.0); // 30% discount
    }

    public double getDiscountPercentage(Programmes programme) {
        return discountPercentages.getOrDefault(programme.getCategories(), 0.0);
    }
}
