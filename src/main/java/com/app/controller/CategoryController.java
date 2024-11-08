package com.app.controller;

import java.util.ArrayList;

import com.app.model.Category;
import com.app.view.CategoryView;
import com.app.dao.CategoryReadDAO;
import com.app.dao.DbConnection;

public class CategoryController {

    DbConnection dbConnection;
    CategoryReadDAO categoryReadDao;
    CategoryView categoryView;

    public CategoryController(DbConnection dbConnection, CategoryReadDAO categoryReadDao, CategoryView categoryView) {
        this.dbConnection = dbConnection;
        this.categoryReadDao = categoryReadDao;
        this.categoryView = categoryView;
    }

    public void loadCategories() {
        ArrayList<Category> categories = categoryReadDao.fetchCategories();
        if(!categories.isEmpty()) categoryView.printCategories(categories);
    }

    public Category searchCategory(){
        Category category = categoryReadDao.searchCategory(categoryView.promptCategoryId());
        return category;
    }

}
