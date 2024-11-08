package com.app.model;

public class Category {

    private int id;
    private String categoryName;
    private String description;

    // Constructor
    public Category(){

    }

    public Category(int id){
        this.id = id;
    }

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Category(int id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
