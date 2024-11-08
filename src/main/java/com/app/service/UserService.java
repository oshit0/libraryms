package com.app.service;

import com.lambdaworks.crypto.SCryptUtil;
import com.app.dao.UserDAO;
import com.app.model.User;
import com.app.user.Role;

/*
 */

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User authenticate(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && SCryptUtil.check(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public boolean registerUser(String username, String password) {
        User user = new User(username, SCryptUtil.scrypt(password, 16384, 8, 1), Role.USER);
        return userDAO.createUser(user);
    }

    public boolean updateUser(String username, String newPassword) {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            user.setPassword(SCryptUtil.scrypt(newPassword, 16384, 8, 1));
            return userDAO.updateUser(user);
        } else {
            return false;
        }
    }
}
