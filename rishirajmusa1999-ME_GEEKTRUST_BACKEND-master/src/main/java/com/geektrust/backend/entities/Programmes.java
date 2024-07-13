package com.geektrust.backend.entities;

public class Programmes {
    private final double price;
    private Category category;
    
    public Programmes(double price, Category category) {
        this.price = price;
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategories() {
        return category;
    }

    @Override
    public String toString() {
        return "Programmes [categories=" + category + ", price=" + price + "]";
    }
}
