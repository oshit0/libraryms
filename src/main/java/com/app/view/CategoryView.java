package com.app.view;

import java.util.ArrayList;
import java.util.Scanner;
import com.app.model.Category;

public class CategoryView {

    private Scanner scn;

    public CategoryView(){
        scn = new Scanner(System.in);
    }

    public void printCategories(ArrayList<Category> categories){
        System.out.println("=".repeat(85));
        System.out.printf("%35s%-10s %-15s %-25s %n", "", "ID", "CATEGORY", "DESCRIPTION");
        System.out.println("=".repeat(85));
        categories.forEach((category) -> {
            System.out.printf("%35s%-10d %-15s %-25s %n", "", category.getId(), category.getCategoryName(), category.getDescription());
        });
        System.out.println("=".repeat(85));
    }

    public int promptCategoryId(){
        System.out.print("Enter Category ID: ");
        try {
            return scn.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Category ID.");
            return -1;
        }
    }

    public void showSuccessMessage(Category category){
        System.out.println("=".repeat(50));
        System.out.println("Successfully added/updated the category:");
        System.out.println(category.toString());
        System.out.println("=".repeat(50));
    }

    public void showErrorMessage(){
        System.out.println("=".repeat(50));
        System.out.println("ERROR: Something went wrong!");
        System.out.println("=".repeat(50));
    }

    public void closeScanner(){
        if (scn != null) {
            scn.close();
        }
    }
}
