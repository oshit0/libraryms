package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookArchiveDAO{

    private final DbConnection dbconnection;

    public BookArchiveDAO(DbConnection dbConnection){
        this.dbconnection = dbConnection;
    }

    private boolean executeUpdateData(int bookId, int userId, String query){
        boolean success = false;
        try (
            Connection connection = dbconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 0) success = true;
        } catch (SQLException e) {
            System.out.println("BookArchiveDao: executeUpdateData - " + e);
        }
        return success;
    }

    public boolean restoreArchivedBook(int bookId){
        String query = "UPDATE tblbooks SET borrower_id = ? WHERE id = ?";
        return executeUpdateData(bookId, 1, query);
    }

    public boolean softDeleteBook(int bookId, int userId){
        String query = "UPDATE tblbooks SET borrower_id = ? WHERE id = ?";
        return executeUpdateData(bookId, userId, query);
    }

    // public boolean hardDeleteBook(int id){
    //     String query = "DELETE FROM tblbooks WHERE id = ?";
    //     return executeUpdateData(id, query);
    // }
}
