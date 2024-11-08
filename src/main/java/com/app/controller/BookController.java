package com.app.controller;

import java.util.ArrayList;

import com.app.model.Book;
import com.app.model.Category;
import com.app.model.User;
import com.app.user.Role;
import com.app.model.Author;
import com.app.view.BookView;
import com.app.dao.BookArchiveDAO;
import com.app.dao.BookReadDAO;
import com.app.dao.BookUpdateDAO;


public class BookController {
    private final BookReadDAO bookReadDao;
    private final BookUpdateDAO bookUpdateDao;
    private final BookArchiveDAO bookArchiveDao;
    private final BookView bookView;
    private final AuthorController authorController;
    private final CategoryController categoryController;
    private User user;
    public int userChoice;

    public BookController(BookReadDAO bookReadDao, BookUpdateDAO bookUpdateDao, BookArchiveDAO bookArchiveDao, BookView bookView,
                          AuthorController authorController, CategoryController categoryController) {
        this.bookReadDao = bookReadDao;
        this.bookUpdateDao = bookUpdateDao;
        this.bookArchiveDao = bookArchiveDao;
        this.bookView = bookView;
        this.authorController = authorController;
        this.categoryController = categoryController;
        user = null;
    }

    public void setCurrentUser(User user){
        this.user = user;
    }

    //User Functions:
    public void loadBooks() {
        ArrayList<Book> books = bookReadDao.retrieveAvailableBooks();
        if(!books.isEmpty()) bookView.printAllBooks(books);
    }

    public void loadBorrowedBooks(){
        ArrayList<Book> books = bookReadDao.retrieveAllBooks();
        if(!books.isEmpty()) bookView.printUserBorrowedBooks(books, user);
    }

    public void findBook(Integer id){
        Book book = bookReadDao.searchBook(id);
        System.out.println((book != null) ? book.toString() : "NULL");
    }

    public void findBook(String title){
        Book book = bookReadDao.searchBook(title);
        System.out.println((book != null) ? book.toString() : "NULL");
    }

    public void findBook(Object obj){
        if (obj instanceof Integer) {
            Integer id = (Integer) obj;
            findBook(id);
        }
        else if (obj instanceof String) {
            String str = (String) obj;
            // findBook(str);
            try {
                Integer id = Integer.parseInt(str);
                System.out.println("Parsed String as Integer (Book ID): " + id);
                findBook(id);
            } catch (NumberFormatException e) {
                System.out.println("String: " + str);
                findBook(str);
            }
        }
        else{
            bookView.showErrorMessage("Try again there was something wrong with your input.");
        }
    }

    public void borrowBook(int id){
        Book book = bookReadDao.searchBook(id);
        if(book != null && book.getBorrowerId() == 1){
            if(bookArchiveDao.softDeleteBook(id, user.getId())){
                bookView.showSuccessMessage(book);
            }
        }
        else{
            bookView.showErrorMessage("Id not found!");
        }
    }

    public void returnBook(int id){
        Book book = bookReadDao.searchBook(id);
        if(book != null && book.getBorrowerId() == user.getId()){
            if(bookArchiveDao.restoreArchivedBook(id)){
                bookView.showSuccessMessage(book);
            }
        }
        else{
            bookView.showErrorMessage("Id not found!");
        }
    }

    //Admin Functions:
    public void loadUsersBooks() {
        ArrayList<Book> books = bookReadDao.retrieveAllBooks();
        if(!books.isEmpty()) bookView.printAllUsersBorrowedBooks(books);
    }

    public void updateBook(Book book, boolean useNewAuthor){
        if(useNewAuthor){
            authorController.createAuthor();
        }

        authorController.loadAuthors();
        Author author = authorController.searchAuthor();
        categoryController.loadCategories();
        Category category = categoryController.searchCategory();

        book.setAuthor(author);
        book.setCategory(category);
        if(book.getId() == 0 && bookUpdateDao.addBook(book)){
            bookView.showSuccessMessage(book);
        }
        else if(bookUpdateDao.updateBook(book)){
            bookView.showSuccessMessage(book);
        }
        else{
            bookView.showErrorMessage("Error on Book Update");
        }
    }

    public void createBook(boolean useNewAuthor){
        bookView.refreshBuffer();
        Book book = bookView.promptBookInfo();
        updateBook(book, useNewAuthor);
    }

    public void editBookInfo(boolean useNewAuthor){
        Book book = new Book();
        int bookId = bookReadDao.searchBook(bookView.promptBookId()).getId();
        bookView.refreshBuffer();
        book = bookView.promptBookInfo();
        book.setId(bookId);
        updateBook(book, useNewAuthor);
    }

    public void restoreBook(int id){
        Book book = bookReadDao.searchBook(id);
        if(book != null){
            bookArchiveDao.restoreArchivedBook(id);
            bookView.showSuccessMessage(book);
        }
        else{
            bookView.showErrorMessage("Id not found!");
        }
    }

    public void displayUserMenu(){
        if(user.getRole() == Role.ADMIN){
            userChoice = bookView.adminMenu();
        }
        else{
            userChoice = bookView.userMenu();
        }
    }

    public boolean handleUserInput(){
        bookView.refreshBuffer();
        if(user.getRole() == Role.ADMIN){
            switch(userChoice){
                case 1:
                    loadBooks();
                    break;
                case 2:
                    findBook(bookView.promptBookSearch());
                    break;
                case 3:
                    createBook(bookView.promptIfExistingAuthor());
                    break;
                case 4:
                    editBookInfo(bookView.promptIfExistingAuthor());
                    break;
                case 5:
                    loadUsersBooks();
                    restoreBook(bookView.promptBookId());
                    break;
                case 6:
                    bookView.showSuccessMessage("Successfully Logged Out!");
                    return true;

            }
        }
        else{
            switch(userChoice){
                case 1:
                    loadBooks();
                    break;
                case 2:
                    findBook(bookView.promptBookSearch());
                    break;
                case 3:
                    borrowBook(bookView.promptBookId());
                    break;
                case 4:
                    loadBorrowedBooks();
                    returnBook(bookView.promptBookId());
                    break;
                case 5:
                    bookView.showSuccessMessage("Successfully Logged Out!");
                    return true;
            }
        }
        return false;
    }

    public void startLibraryMs(){
        boolean hasLoggedOut = false;
        while(!hasLoggedOut){
            displayUserMenu();
            hasLoggedOut = handleUserInput();
        }
    }


}
