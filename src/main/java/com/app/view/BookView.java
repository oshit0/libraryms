package com.app.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.app.model.Book;
import com.app.model.User;

public class BookView {

    private Scanner scn;

    public BookView(){
        scn = new Scanner(System.in);
    }


    public void printAllBooks(ArrayList<Book> books){
        System.out.println("=".repeat(85));
        System.out.printf("%-10s %-50s %-15s %n", "ID", "TITLE", "AUTHOR");
        System.out.println("=".repeat(85));
        books.forEach((book) -> {
            System.out.printf("%-10d %-50s %-15s %n", book.getId(), book.getTitle(), book.getAuthor().getAuthorName());
        });
        System.out.println("=".repeat(85));
    }

    public void printBook(Book book){
        System.out.println("=".repeat(85));
        System.out.printf("%-10s %-50s %-15s %n", "ID", "TITLE", "AUTHOR");
        System.out.println("=".repeat(85));
        System.out.printf("%-10d %-50s %-15s %n", book.getId(), book.getTitle(), book.getAuthor().getAuthorName());
        System.out.println("=".repeat(85));
    }

    public void printUserBorrowedBooks(ArrayList<Book> books, User user){
        System.out.println("=".repeat(85));
        System.out.printf("%-10s %-50s %-15s %n", "ID", "TITLE", "AUTHOR");
        System.out.println("=".repeat(85));
        books.forEach((book) -> {
            if(book.getBorrowerId() == user.getId()){
                System.out.printf("%-10d %-50s %-15s %n", book.getId(), book.getTitle(), book.getAuthor().getAuthorName());
            }
        });
        System.out.println("=".repeat(85));
    }

    public void printAllUsersBorrowedBooks(ArrayList<Book> books){
        System.out.println("=".repeat(85));
        System.out.printf("%-10s %-50s %-15s %n", "ID", "TITLE", "BORROWER_ID");
        System.out.println("=".repeat(85));
        books.forEach((book) -> {
            if(book.getBorrowerId() != 1){
                System.out.printf("%-10s %-50s %-15s %n", book.getId(), book.getTitle(), book.getBorrowerId());
            }
        });
        System.out.println("=".repeat(85));
    }

    public int userMenu(){
        System.out.println("=".repeat(40));
        System.out.println(" ".repeat(12) + "User Menu Options" + " ".repeat(12));
        System.out.println("=".repeat(40));
        try {
            System.out.println("1 - List Books");
            System.out.println("2 - Search Book");
            System.out.println("3 - Borrow Books");
            System.out.println("4 - Return Books");
            System.out.println("5 - Logout");
            System.out.print("Choose an option: ");
            return scn.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public int adminMenu(){
        System.out.println("=".repeat(40));
        System.out.println(" ".repeat(12) + "Admin Menu Options" + " ".repeat(12));
        System.out.println("=".repeat(40));
        try {
            System.out.println("1 - List Books");
            System.out.println("2 - Search Book");
            System.out.println("3 - Add Books");
            System.out.println("4 - Update Books");
            System.out.println("5 - Restore Books");
            System.out.println("6 - Logout");
            System.out.print("Choose an option: ");
            return scn.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    public boolean promptIfExistingAuthor(){
        System.out.print("Create New Author or Use Existing? (yes - 1, no - 0): ");
        try {
            return scn.nextInt() == 1;
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to 'no'.");
            return false;
        }
    }

    public Book promptBookInfo(){
        Book book = new Book();
        try {
            System.out.print("Enter Book Title: ");
            book.setTitle(scn.nextLine());
            System.out.print("Enter Book Pages: ");
            book.setPages(scn.nextInt());
            refreshBuffer();
            System.out.print("Enter Book Edition: ");
            book.setEdition(scn.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input encountered while entering book details.");
        }
        return book;
    }

    public int promptBookId(){
        System.out.print("Enter the Book ID: ");
        try {
            return scn.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Book ID.");
            return -1;
        }
    }

    public Object promptBookSearch(){
        System.out.print("Enter Book ID or Title Substring: ");
        try {
            return scn.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid search query.");
            return null;
        }
    }

    public void showSuccessMessage(String message){
        System.out.println("=".repeat(50));
        System.out.println(" " + message);
        System.out.println("=".repeat(50));
    }

    public void showSuccessMessage(Book book){
        System.out.println("=".repeat(50));
        System.out.println("Book successfully processed: " + book);
        System.out.println("=".repeat(50));
    }

    public void showErrorMessage(String message){
        System.out.println("=".repeat(50));
        System.out.println("Error: " + message);
        System.out.println("=".repeat(50));
    }

    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void refreshBuffer(){
        scn.nextLine();
    }

    public void closeScanner() {
        if (scn != null) {
            scn.close();
        }
    }
}
