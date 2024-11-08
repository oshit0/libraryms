package com.app.main;

import com.app.controller.AuthorController;
import com.app.controller.BookController;
import com.app.controller.CategoryController;
import com.app.controller.UserController;
import com.app.dao.AuthorReadDAO;
import com.app.dao.AuthorUpdateDAO;
import com.app.dao.BookArchiveDAO;
import com.app.dao.BookReadDAO;
import com.app.dao.BookUpdateDAO;
import com.app.dao.CategoryReadDAO;
import com.app.dao.DbConnection;
import com.app.dao.UserDAO;
import com.app.model.User;
import com.app.service.UserService;
import com.app.view.AuthorView;
import com.app.view.BookView;
import com.app.view.CategoryView;
import com.app.view.UserView;

public class Main {
    public static void main(String[] args) {
        //TODO Remove tight coupling on the dependency injection
        //TODO Bind Borrow FK
        //TODO Interface frontend
        //TODO UML

        //Scanner inputmismatch catch
        DbConnection dbConnection = new DbConnection();

        AuthorReadDAO authorReadDao = new AuthorReadDAO(dbConnection);
        AuthorUpdateDAO authorUpdateDao = new AuthorUpdateDAO(dbConnection);
        AuthorView authorView = new AuthorView();
        AuthorController authorController = new AuthorController(dbConnection, authorReadDao, authorUpdateDao, authorView);

        CategoryReadDAO categoryReadDao = new CategoryReadDAO(dbConnection);
        CategoryView categoryView = new CategoryView();
        CategoryController categoryController = new CategoryController(dbConnection, categoryReadDao, categoryView);

        UserDAO userDao = new UserDAO(dbConnection);
        UserService userService = new UserService(userDao);
        UserView userView = new UserView();
        UserController userController = new UserController(userService, userView);

        BookReadDAO bookReadDao = new BookReadDAO(dbConnection);
        BookUpdateDAO bookUpdateDao = new BookUpdateDAO(dbConnection);
        BookArchiveDAO bookArchive = new BookArchiveDAO(dbConnection);
        BookView bookView = new BookView();
        BookController bookController = new BookController(bookReadDao, bookUpdateDao, bookArchive, bookView, authorController, categoryController);

        User user = new User();
        user = userController.startAuthMenu();
        bookController.setCurrentUser(user);
        bookController.startLibraryMs();

        authorView.closeScanner();
        categoryView.closeScanner();
        userView.closeScanner();
        bookView.closeScanner();
    }
}