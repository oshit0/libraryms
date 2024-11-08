package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.model.Author;

public class AuthorUpdateDAO {

    private final DbConnection dbconnection;

    public AuthorUpdateDAO(DbConnection dbConnection){
        this.dbconnection = dbConnection;
    }

    private boolean executeUpdateData(Author author, String query){
        boolean success = false;
        try (
            Connection connection = dbconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.setString(2, author.getPhone());
            preparedStatement.setString(3, author.getAddress());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 0) success = true;
        } catch (SQLException e) {
            System.out.println("AuthorUpdateDao: executeUpdataData - " + e);
        }
        return success;
    }

    public boolean addAuthor(Author author){
        String query = "INSERT INTO tblauthors (author_name, author_phone, author_address) "
                        + "VALUES (?, ?, ?)";
        return executeUpdateData(author, query);
    }

    public boolean updateAuthor(Author author){
        String query = "UPDATE tblauthors SET author_name = ?, author_phone = ?, author_address = ? ";
        return executeUpdateData(author, query);
    }
}
