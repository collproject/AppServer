/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.dao.service;

import com.pb.shop.model.Product;
import java.util.List;

/**
 *
 * @author Madness
 */
public interface ProductDaoService {
    List<Product> getAllProducts();
    Product getProductById(String prodId);
    List<Product> getProductsByName(String name);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String prodId);
}
