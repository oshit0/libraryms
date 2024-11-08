package com.app.controller;

import com.app.model.User;
import com.app.service.UserService;
import com.app.view.UserView;

public class UserController {

    private UserService userService;
    private UserView userView;
    private User user;
    private int userChoice;

    public UserController(UserService userService, UserView userView) {
        this.userService = userService;
        this.userView = userView;
    }

    public boolean login() {
        userView.refreshBuffer();
        String username = userView.promptUsername();
        String password = userView.promptPassword();
        User user = userService.authenticate(username, password);
        if(user != null){
            userView.displaySuccessMessage("Welcome back " + user.getUsername() + "!");
            this.user = user;
            return true;
        }
        else{
            userView.displayErrorMessage("Invalid username or password.");
            this.user = user;
            return false;
        }
    }

    public boolean register() {
        userView.refreshBuffer();
        User user = null;
        String tmp;
        boolean success = false;
        while(true){
            user = userView.promptUserDetails();
            tmp = userView.promptPassword();
            if(user.getPassword().equals(tmp)) break;
            else userView.displayErrorMessage("Passwords don't match, try again!");
        }
        success = userService.registerUser(user.getUsername(), user.getPassword());
        if(success){
            userView.displayUserProfile(user);
            userView.displaySuccessMessage("Created a new account successfully.");
            return true;
        }
        else{
            userView.displayErrorMessage("Error creating account please try again.");
            return false;
        }
    }

    public void changePassword(){
        String username = userView.promptUsername();
        String newPassword, tmp;
        while(true){
            newPassword = userView.promptPassword();
            tmp = userView.promptPassword();
            if(newPassword.equals(tmp)) break;
            else userView.displayErrorMessage("Passwords don't match, try again!");
        }
        if(userService.updateUser(username, newPassword))
            userView.displaySuccessMessage("Password Changed Successfully.");
        else
            userView.displayErrorMessage("There was an error, please try again.");
    }

    public void displayUserMenu(){
        userChoice = userView.promptUserChoice();
    }

    public boolean handleUserInput(){
        switch(userChoice){
            case 1:
                return login();
            case 2:
                register();
        }
        return false;
    }

    public User startAuthMenu(){
        userView.printTitle();
        boolean loggedIn = false;
        while(!loggedIn){
            displayUserMenu();
            loggedIn = handleUserInput();
        }
        return this.user;
    }
}
