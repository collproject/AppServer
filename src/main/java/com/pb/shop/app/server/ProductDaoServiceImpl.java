/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Madness
 */
public class ProductDaoServiceImpl extends JdbcDaoSupport implements ProductDaoService {

    private final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE prodid = ?";
    private final String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE prodname like ?";
    private final String ADD_PRODUCT = "INSERT INTO products (prodid, makid, catid,"
            + " prodname, prodprice, proddescription, prodimg, prodexist) VALUES(?,?,?,?,?,?,?,?)";
    private final String UPDATE_PRODUCT = "UPDATE products SET makid = ?, catid = ?, "
            + "prodname = ?, prodprice = ?, proddescription = ?, prodimg = ?, "
            + "prodexist = ? WHERE prodid = ?";
    private final String DELETE_BY_ID = "DELETE FROM Products WHERE ProdID = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Product> getAllProducts() {
        return getJdbcTemplate().query(GET_ALL_PRODUCTS, new ProductMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product getProductById(String prodId) {
        return getJdbcTemplate().query(
                GET_PRODUCT_BY_ID,
                new Object[]{Integer.parseInt(prodId)},
                new ProductMapper()).get(0);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Product> getProductsByName(String name) {
        return getJdbcTemplate().query(
                GET_PRODUCT_BY_NAME,
                new Object[]{"%" + name + "%"},
                new ProductMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addProduct(Product product) {
        getJdbcTemplate().update(
                ADD_PRODUCT,
                new Object[]{
            product.getProdID(),
            product.getCatID(),
            product.getMakID(),
            product.getProdName(),
            product.getProdPrice(),
            product.getProdDescription(),
            product.getProdImg(),
            product.getProdExist()
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProduct(Product product) {
        getJdbcTemplate().update(
                UPDATE_PRODUCT,
                new Object[]{
            product.getCatID(),
            product.getMakID(),
            product.getProdName(),
            product.getProdPrice(),
            product.getProdDescription(),
            product.getProdImg(),
            product.getProdExist(),
            product.getProdID()
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteProduct(String prodId) {
        getJdbcTemplate().update(DELETE_BY_ID, 
                new Object[]{new Integer(prodId)}
        );
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProdID(rs.getInt("ProdID"));
            product.setCatID(rs.getInt("CatID"));
            product.setMakID(rs.getInt("MakID"));
            product.setProdName(rs.getString("ProdName"));
            product.setProdDescription(rs.getString("ProdDescription"));

            if (rs.getString("ProdExist").equals("y")) {
                product.setProdExist(true);
            } else {
                product.setProdExist(false);
            }

            product.setProdImg(rs.getString("ProdImg"));
            product.setProdPrice(rs.getBigDecimal("ProdPrice"));
            return product;
        }
    }
}
