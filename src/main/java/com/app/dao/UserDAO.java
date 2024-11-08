package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.User;
import com.app.user.Role;

public class UserDAO {
    private DbConnection dbConnection;

    public UserDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public User getUser(String query, Object param){
        User user = null;
        try (
            Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            if(param instanceof Integer){
                preparedStatement.setInt(1, (Integer) param);
            }
            else if(param instanceof String){
                preparedStatement.setString(1, (String) param);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    user = new User(resultSet.getInt("id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    (resultSet.getInt("admin") == 0 ? Role.USER : Role.ADMIN));
                }
                else{
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("UserDAO: getUserById - " + e);
            }
        } catch (SQLException e) {
            System.out.println("UserDAO: getUserById - " + e);
        }
        return user;
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM tblusers "
                        + "WHERE username LIKE ?";
        username = "%" + username + "%";
        return getUser(query, username);
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM tblusers "
                        + "WHERE id = ?";
        return getUser(query, id);
    }

    public boolean executeUpdateData(User user, String query){
        boolean success = false;
        try (
            Connection connection = dbConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            if(query.contains("WHERE")){
                preparedStatement.setInt(3, user.getId());
            }
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected != 0) success = true;
        } catch (SQLException e) {
            System.out.println("UserDAO: executeUpdate - " + e);
        }
        return success;
    }

    public boolean createUser(User user) {
        String query = "INSERT INTO tblusers (username, password) "
                        + "VALUES (?, ?)";
        return executeUpdateData(user, query);
    }

    public boolean updateUser(User user) {
        String query = "UPDATE tblusers SET username = ?, password = ? WHERE id = ? ";
        return executeUpdateData(user, query);
    }
}

