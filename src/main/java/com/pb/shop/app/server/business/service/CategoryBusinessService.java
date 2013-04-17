/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.service;

import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Category;
import com.pb.shop.model.UserGoodMessage;
import java.util.List;

/**
 *
 * @author Дмитрий
 */
public interface CategoryBusinessService {
    
    List<Category> getAllCategories() throws GeneralException;
    Category getCategoryById(String catId) throws GeneralException;
    List<Category> getCategoriesByName(String name) throws GeneralException;
    UserGoodMessage addCategory(Category category)throws GeneralException;
    UserGoodMessage updateCategory(Category category)throws GeneralException;
    UserGoodMessage deleteCategory(String catId) throws GeneralException;
    
}
