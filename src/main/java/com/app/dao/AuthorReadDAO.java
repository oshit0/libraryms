package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.model.Author;

public class AuthorReadDAO {

    private final DbConnection dbconnection;

    public AuthorReadDAO(DbConnection dbConnection){
        this.dbconnection = dbConnection;
    }

    public ArrayList<Author> fetchAuthors(){
        String query = "SELECT * FROM tblauthors";
        ArrayList<Author> authors = new ArrayList<>();
        try (
            Connection connection = dbconnection.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            while(resultSet.next()){
                authors.add(new Author(resultSet.getInt("id"),
                                    resultSet.getString("author_name"),
                                    resultSet.getString("author_phone"),
                                    resultSet.getString("author_address")));
            }
        } catch (SQLException e) {
            System.out.println("AuthorReadDao: fetchAuthors - " + e);
        }
        return authors;
    }

    public Author searchAuthor(int id){
        String query = "SELECT * FROM tblauthors WHERE id = ?";
        Author author = null;
        try (
            Connection connection = dbconnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                author = new Author(resultSet.getInt("id"),
                                    resultSet.getString("author_name"),
                                    resultSet.getString("author_phone"),
                                    resultSet.getString("author_address"));
                }
                else{
                    return null;
                }
            } catch (SQLException e) {
                System.out.println("AuthorReadDao: searchAuthor - " + e);
            }
        } catch (SQLException e) {
            System.out.println("AuthorReadDao: searchAuthor - " + e);
        }
        return author;
    }
}