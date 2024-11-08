package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.Book;
import com.app.model.Category;
import com.app.model.Author;
import com.app.model.Publisher;

public class BookReadDAO {

    private final DbConnection dbConnection;

    public BookReadDAO(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public ArrayList<Book> fetchBooks(String query){
        ArrayList<Book> books = new ArrayList<>();
        try (
            Connection connection = dbConnection.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            while(resultSet.next()){
                books.add(new Book(resultSet.getInt("id"),
                                    resultSet.getString("book_title"),
                                    new Author(resultSet.getString("author_name")),
                                    new Category(resultSet.getString("category")),
                                    new Publisher(resultSet.getString("publisher_name")),
                                    resultSet.getInt("pages"),
                                    resultSet.getString("edition"),
                                    resultSet.getInt("borrower_id")));
            }
        } catch (SQLException e) {
            System.out.println("BookReadDao: fetchBooks - " + e);
        }
        return books;
    }

    public ArrayList<Book> retrieveAllBooks(){
        String query = "SELECT tblbooks.id, tblbooks.book_title, tblauthors.author_name, tblcategories.category, tblpublishers.publisher_name, pages, edition, borrower_id, tblusers.username FROM tblbooks "
                        + "INNER JOIN tblauthors ON tblbooks.author_id = tblauthors.id "
                        + "INNER JOIN tblcategories ON tblbooks.category_id = tblcategories.id "
                        + "INNER JOIN tblpublishers ON tblbooks.publisher_id = tblpublishers.id "
                        + "INNER JOIN tblusers on tblbooks.borrower_id = tblusers.id ";
        return fetchBooks(query);
    }

    public ArrayList<Book> retrieveAvailableBooks(){
        String query = "SELECT tblbooks.id, tblbooks.book_title, tblauthors.author_name, tblcategories.category, tblpublishers.publisher_name, pages, edition, borrower_id, tblusers.username FROM tblbooks "
                        + "INNER JOIN tblauthors ON tblbooks.author_id = tblauthors.id "
                        + "INNER JOIN tblcategories ON tblbooks.category_id = tblcategories.id "
                        + "INNER JOIN tblpublishers ON tblbooks.publisher_id = tblpublishers.id "
                        + "INNER JOIN tblusers on tblbooks.borrower_id = tblusers.id "
                        + "WHERE tblbooks.borrower_id = 1";
        return fetchBooks(query);
    }

    public Book retrieveBook(String query, Object param){
        Book book = null;
        try (
            Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            if (param instanceof Integer) {
                preparedStatement.setInt(1, (Integer) param);
            } else if (param instanceof String) {
                preparedStatement.setString(1, (String) param);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    book = new Book(resultSet.getInt("id"),
                                        resultSet.getString("book_title"),
                                        new Author(resultSet.getString("author_name")),
                                        new Category(resultSet.getString("category")),
                                        new Publisher(resultSet.getString("publisher_name")),
                                        resultSet.getInt("pages"),
                                        resultSet.getString("edition"),
                                        resultSet.getInt("borrower_id"));
                }
                else{
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("BookReadDao: searchBook - " + e);
            }
        } catch (SQLException e) {
            System.out.println("BookReadDao: searchBook - " + e);
        }
        return book;
    }

    public Book searchBook(String title){
        String query = "SELECT tblbooks.id, tblbooks.book_title, tblauthors.author_name, tblcategories.category, tblpublishers.publisher_name, pages, edition, borrower_id, tblusers.username FROM tblbooks "
                        + "INNER JOIN tblauthors ON tblbooks.author_id = tblauthors.id "
                        + "INNER JOIN tblcategories ON tblbooks.category_id = tblcategories.id "
                        + "INNER JOIN tblpublishers ON tblbooks.publisher_id = tblpublishers.id "
                        + "INNER JOIN tblusers on tblbooks.borrower_id = tblusers.id "
                        + "WHERE book_title LIKE ?";
        title = "%" + title + "%";
        return retrieveBook(query, title);
    }

    public Book searchBook(int id){
        String query = "SELECT tblbooks.id, tblbooks.book_title, tblauthors.author_name, tblcategories.category, tblpublishers.publisher_name, pages, edition, borrower_id, tblusers.username FROM tblbooks "
                        + "INNER JOIN tblauthors ON tblbooks.author_id = tblauthors.id "
                        + "INNER JOIN tblcategories ON tblbooks.category_id = tblcategories.id "
                        + "INNER JOIN tblpublishers ON tblbooks.publisher_id = tblpublishers.id "
                        + "INNER JOIN tblusers on tblbooks.borrower_id = tblusers.id "
                        + "WHERE tblbooks.id = ?";
        return retrieveBook(query, id);

    }

}

