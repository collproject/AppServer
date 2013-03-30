/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author Madness
 */
public class CategoryDaoServiceImpl extends JdbcDaoSupport implements CategoryDaoService {

    private final String GET_ALL_CATEGORIES = "SELECT * FROM Category";
    private final String GET_CATEGORY_BY_ID = "SELECT * FROM Category WHERE CatID = ?";
    private final String GET_CATEGORY_BY_NAME = "SELECT * FROM Category WHERE CatName = ?";
    private final String ADD_CATEGORY = "INSERT INTO Category(CatID, ParentCatID, CatName) VALUES(?, ?, ?)";
    private final String UPDATE_CATEGORY = "UPDATE Category SET ParentCatID = ?, CatName = ? WHERE CatID = ?";

    @Override
    public List<Category> getAllCategories() {
        return getJdbcTemplate().query(GET_ALL_CATEGORIES, new CategoryWrapper());
    }

    @Override
    public Category getCategoryById(String catId) {
        List<Category> list = getJdbcTemplate().query(GET_CATEGORY_BY_ID,
                new Object[]{catId},
                new CategoryWrapper());
        return list.get(0);
    }

    @Override
    public List<Category> getCategoriesByName(String name) {
        List<Category> list = getJdbcTemplate().query(GET_CATEGORY_BY_NAME,
                new Object[]{name},
                new CategoryWrapper());
        return list;
    }

    @Override
    public void addCategory(Category category) {
        getJdbcTemplate().update(ADD_CATEGORY,
                new Object[]{category.getCatID(),
                    category.getParentCatID(),
                    category.getCatName()}
         );
    }

    @Override
    public void updateCategory(Category category) {
        getJdbcTemplate().update(UPDATE_CATEGORY,
                new Object[]{category.getCatID(),
                    category.getParentCatID(),
                    category.getCatName()}
        );
    }

    public static final class CategoryWrapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setCatID(rs.getInt("CatID"));
            category.setParentCatID(rs.getInt("ParentCatID"));
            category.setCatName(rs.getString("CatName"));
            return category;
        }
    }
}
