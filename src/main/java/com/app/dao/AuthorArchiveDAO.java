package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorArchiveDAO {

    private final DbConnection dbconnection;

    public AuthorArchiveDAO(DbConnection dbConnection){
        this.dbconnection = dbConnection;
    }

    private boolean executeUpdateData(int id, String query){
        boolean success = false;
        try (
            Connection connection = dbconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 0) success = true;
        } catch (SQLException e) {
            System.out.println("AuthorArchiveDao: executeUpdateData - " + e);
        }
        return success;
    }

    public boolean restoreArchivedAuthor(int id){
        String query = "UPDATE tblauthors SET archived = 0 WHERE id = ?";
        return executeUpdateData(id, query);
    }

    public boolean softDeleteAuthor(int id){
        String query = "UPDATE tblauthors SET archived = 1 WHERE id = ?";
        return executeUpdateData(id, query);
    }

    public boolean hardDeleteAuthor(int id){
        String query = "DELETE FROM tblauthors WHERE id = ?";
        return executeUpdateData(id, query);
    }

}