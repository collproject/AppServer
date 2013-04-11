/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.dao.service;

import com.pb.shop.model.Category;
import java.util.List;

/**
 *
 * @author Madness
 */
public interface CategoryDaoService {
    
    List<Category> getAllCategories();
    Category getCategoryById(String catId);
    List<Category> getCategoriesByName(String name);
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(String catId);
}
