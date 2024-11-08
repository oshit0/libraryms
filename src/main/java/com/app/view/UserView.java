package com.app.view;

import java.util.List;
import java.util.Scanner;

import com.app.model.User;

public class UserView {

    Scanner scn;

    public UserView() {
        scn = new Scanner(System.in);
    }

    public void printTitle() {
        System.out.println(" _     _ _                                                     ");
        System.out.println("| |   (_) |__  _ __ __ _ _ __ _   _                           ");
        System.out.println("| |   | | '_ \\| '__/ _` | '__| | | |                          ");
        System.out.println("| |___| | |_) | | | (_| | |  | |_| |                          ");
        System.out.println("|_____|_|_.__/|_|  \\__,_|_|   \\__, |                      _   ");
        System.out.println("|  \\/  | __ _ _ __   __ _  __ |___/_ _ __ ___   ___ _ __ | |_ ");
        System.out.println("| |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|");
        System.out.println("| |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ ");
        System.out.println("|_|__|_|\\__,_|_| |_|\\__,_|\\__, |___|_| |_| |_|\\___|_| |_|\\__|");
        System.out.println("/ ___| _   _ ___| |_ ___ _|___/__                              ");
        System.out.println("\\___ \\| | | / __| __/ _ \\ '_ ` _ \\                            ");
        System.out.println(" ___) | |_| \\__ \\ ||  __/ | | | | |                           ");
        System.out.println("|____/ \\__, |___/\\__\\___|_| |_| |_|                           ");
        System.out.println("       |___/                                                  ");
    }

    public String promptUsername() {
        try {
            System.out.print("Enter username: ");
            return scn.nextLine();
        } catch (Exception e) {
            displayErrorMessage("Invalid input for username.");
            return promptUsername();  // Retry the input if an exception occurs
        }
    }

    public String promptPassword() {
        try {
            System.out.print("Enter password: ");
            return scn.nextLine();
        } catch (Exception e) {
            displayErrorMessage("Invalid input for password.");
            return promptPassword();  // Retry the input if an exception occurs
        }
    }

    public void displayErrorMessage(String message) {
        System.out.println("=".repeat(50));
        System.out.println("Error: " + message);
        System.out.println("=".repeat(50));
    }

    public void displaySuccessMessage(String message) {
        System.out.println("=".repeat(50));
        System.out.println("Success: " + message);
        System.out.println("=".repeat(50));
    }

    public void displayLoginPrompt() {
        System.out.println("=".repeat(50));
        System.out.println("Please log in.");
        System.out.println("=".repeat(50));
    }

    public void displayRegistrationPrompt() {
        System.out.println("=".repeat(50));
        System.out.println("Please register.");
        System.out.println("=".repeat(50));
    }

    public void displayWelcomeMessage() {
        System.out.println("=".repeat(50));
        System.out.println("Welcome back!");
        System.out.println("=".repeat(50));
    }

    public void displayInvalidCredentialsMessage() {
        System.out.println("=".repeat(50));
        System.out.println("Invalid username or password.");
        System.out.println("=".repeat(50));
    }

    public int promptUserChoice() {
        try {
            System.out.println("=".repeat(50));
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");
            return scn.nextInt();
        } catch (Exception e) {
            displayErrorMessage("Invalid choice input.");
            scn.nextLine(); // Clear the buffer
            return promptUserChoice();  // Retry the input if an exception occurs
        }
    }

    public User promptUserDetails() {
        String username = promptUsername();
        String password = promptPassword();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public void displayUserList(List<User> users) {
        System.out.println("=".repeat(50));
        users.forEach((user) -> {
            System.out.println("User ID: " + user.getId() + ", Username: " + user.getUsername());
        });
        System.out.println("=".repeat(50));
    }

    public void displayUserProfile(User user) {
        System.out.println("=".repeat(50));
        System.out.println("User ID: " + user.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("=".repeat(50));
    }

    public void displayGoodbyeMessage() {
        System.out.println("=".repeat(50));
        System.out.println("Goodbye!");
        System.out.println("=".repeat(50));
    }

    public boolean promptForConfirmation(String message) {
        try {
            System.out.print(message + " (y/n): ");
            char choice = scn.next().charAt(0);
            return choice == 'y' || choice == 'Y';
        } catch (Exception e) {
            displayErrorMessage("Invalid confirmation input.");
            return promptForConfirmation(message);  // Retry the input if an exception occurs
        }
    }

    public void refreshBuffer() {
        scn.nextLine();
    }

    public void closeScanner() {
        if (scn != null) {
            scn.close();
        }
    }
}
