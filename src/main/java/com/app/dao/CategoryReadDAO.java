package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.model.Category;

public class CategoryReadDAO {
    private final DbConnection dbconnection;

    public CategoryReadDAO(DbConnection dbConnection){
        this.dbconnection = dbConnection;
    }

    public ArrayList<Category> fetchCategories(){
        String query = "SELECT * FROM tblcategories";
        ArrayList<Category> categories = new ArrayList<>();
        try (
            Connection connection = dbconnection.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            while(resultSet.next()){
                categories.add(new Category(resultSet.getInt("id"),
                                    resultSet.getString("category"),
                                    resultSet.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println("CategoryReadDao: fetchCategories " + e);
        }
        return categories;
    }

    public Category searchCategory(int id){
        String query = "SELECT * FROM tblcategories WHERE id = ?";
        Category category = null;
        try (
            Connection connection = dbconnection.connect();
            PreparedStatement preparedStatement= connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                category = new Category(resultSet.getInt("id"),
                                    resultSet.getString("category"),
                                    resultSet.getString("description"));
                }
                else{
                    return null;
                }
            } catch (SQLException e) {
            System.out.println("CategoryReadDao: searchCategories - " + e);
            }
        } catch (SQLException e) {
            System.out.println("CategoryReadDao: searchCategories - " + e);
        }
        return category;
    }
}
