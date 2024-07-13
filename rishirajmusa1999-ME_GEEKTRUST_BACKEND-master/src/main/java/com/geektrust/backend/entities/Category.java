package com.geektrust.backend.entities;

public enum Category {
    DIPLOMA("Diploma"),
    CERTIFICATION("Certification"),
    DEGREE("Degree"),
    NOT_FOUND("NOT_FOUND");

    private String name;

    Category(String name){
        this.name=name;
    }


    public static Category getCategoryByName(String programName){
        for(Category category: values()){
            if(category.name.equalsIgnoreCase(programName)){
                return category;
            }
        }
        return Category.NOT_FOUND;
    }
}
