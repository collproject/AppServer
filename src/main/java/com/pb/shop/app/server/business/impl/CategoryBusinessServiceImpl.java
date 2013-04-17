/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.impl;

import com.pb.shop.app.server.business.service.CategoryBusinessService;
import com.pb.shop.app.server.dao.service.CategoryDaoService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Category;
import com.pb.shop.model.UserGoodMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Дмитрий
 */
@Service
public class CategoryBusinessServiceImpl implements CategoryBusinessService {

    @Autowired
    @Qualifier("categoryImpl")
    CategoryDaoService categoryDaoService;

    @Override
    public List<Category> getAllCategories() throws GeneralException {
        try {
            List<Category> list = categoryDaoService.getAllCategories();
            if (list.isEmpty()) {
                throw new GeneralException("Нет ни одной категории.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public Category getCategoryById(String catId) throws GeneralException {
        try {
            try{
                Integer.parseInt(catId);
            } catch(Exception e){
                throw new GeneralException("ИН \"" + catId + "\" не коректен.");
            }
            
            Category category = categoryDaoService.getCategoryById(catId);
            if(category == null){
                throw new GeneralException("Категория с таким ИН отсутствует.");
            }
            return category;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public List<Category> getCategoriesByName(String name) throws GeneralException {
        try {
            List<Category> list = categoryDaoService.getCategoriesByName(name);
            if (list.isEmpty()) {
                throw new GeneralException("Категория с таким именем отсутствует.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage addCategory(Category category) throws GeneralException {
        try {
            categoryDaoService.addCategory(category);
            return new UserGoodMessage("Категория успешно добавлена.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage updateCategory(Category category) throws GeneralException {
        try {
            categoryDaoService.updateCategory(category);
            return new UserGoodMessage("Категория успешно обновлена.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public UserGoodMessage deleteCategory(String catId) throws GeneralException {
        try {
            Integer.parseInt(catId);
            categoryDaoService.deleteCategory(catId);
            return new UserGoodMessage("Категория успешно удалена.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
}
