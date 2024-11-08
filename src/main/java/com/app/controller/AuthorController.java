package com.app.controller;

import java.util.ArrayList;

import com.app.model.Author;
import com.app.view.AuthorView;
import com.app.dao.DbConnection;
import com.app.dao.AuthorReadDAO;
import com.app.dao.AuthorUpdateDAO;
// import com.app.dao.AuthorArchiveDao;

public class AuthorController {
    DbConnection dbConnection;
    private AuthorReadDAO authorReadDao;
    private AuthorUpdateDAO authorUpdateDao;
    // private AuthorArchiveDao authorArchiveDao;
    private AuthorView authorView;

    public AuthorController(DbConnection dbConnection, AuthorReadDAO authorReadDao,
                            AuthorUpdateDAO authorUpdateDao, AuthorView authorView) {
        this.dbConnection = dbConnection;
        this.authorReadDao = authorReadDao;
        this.authorUpdateDao = authorUpdateDao;
        this.authorView = authorView;
    }

    public void loadAuthors() {
        ArrayList<Author> authors = authorReadDao.fetchAuthors();
        if(!authors.isEmpty()) authorView.printAuthors(authors);
    }

    //TODO fix this:
    public Author searchAuthor(){
        Author author = authorReadDao.searchAuthor(authorView.promptAuthorId());
        return author;
    }

    public void createAuthor(){
        Author author = new Author();
        author = authorView.promptAuthorInfo();
        if(authorUpdateDao.addAuthor(author)){
            authorView.showSuccessMessage(author);
        }
        else{
            authorView.showErrorMessage();
        }
    }
}
