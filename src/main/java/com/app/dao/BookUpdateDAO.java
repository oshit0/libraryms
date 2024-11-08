package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.model.Book;

public class BookUpdateDAO {

    private final DbConnection dbConnection;

    public BookUpdateDAO(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    private boolean executeUpdateData(Book book, String query){
        boolean success = false;
        try (
            Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthor().getId());
            preparedStatement.setInt(3, book.getCategory().getId());
            preparedStatement.setInt(4, 1);
            preparedStatement.setInt(5, book.getPages());
            preparedStatement.setString(6, book.getEdition());
            if(query.contains("WHERE")){
                preparedStatement.setInt(7, book.getId());
            }
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 0) success = true;
        } catch (SQLException e) {
            System.out.println("BookUpdateDao: executeUpdateData - " + e);
        }
        return success;
    }

    public boolean addBook(Book book){
        String query = "INSERT INTO tblbooks (book_title, author_id, category_id, publisher_id, pages, edition) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdateData(book, query);
    }

    public boolean updateBook(Book book){
        String query = "UPDATE tblbooks SET book_title = ?, author_id = ?, category_id = ?, publisher_id = ?, pages = ?, edition = ? WHERE id = ?";
        return executeUpdateData(book, query);
    }
}
