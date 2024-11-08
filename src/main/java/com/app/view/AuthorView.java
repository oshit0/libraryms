package com.app.view;

import java.util.ArrayList;
import java.util.Scanner;
import com.app.model.Author;

public class AuthorView {

    private Scanner scn;

    public AuthorView(){
        scn = new Scanner(System.in);
    }

    public void printAuthors(ArrayList<Author> authors){
        System.out.println("=".repeat(85));
        System.out.printf("%35s%-10s %-25s %-15s %-15s %n", "", "ID", "AUTHOR", "PHONE", "ADDRESS");
        System.out.println("=".repeat(85));
        authors.forEach((author) -> {
            System.out.printf("%35s%-10d %-25s %-15s %-15s %n", "", author.getId(), author.getAuthorName(), author.getPhone(), author.getAddress());
        });
        System.out.println("=".repeat(85));
    }

    public Author promptAuthorInfo(){
        Author author = new Author();
        try {
            System.out.print("Enter author's name: ");
            author.setAuthorName(scn.nextLine());

            System.out.print("Enter author's phone number: ");
            author.setPhone(scn.nextLine());

            System.out.print("Enter author's address: ");
            author.setAddress(scn.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input encountered while entering author details.");
        }
        return author;
    }

    public int promptAuthorId(){
        System.out.print("Enter Author ID: ");
        try {
            return scn.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Author ID.");
            return -1;
        }
    }

    public void showSuccessMessage(Author author){
        System.out.println("=".repeat(50));
        System.out.println("Successfully processed author: " + author);
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
