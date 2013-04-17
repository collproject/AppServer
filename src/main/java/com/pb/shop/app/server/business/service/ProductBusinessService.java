/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.service;

import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Product;
import com.pb.shop.model.UserGoodMessage;
import java.util.List;

/**
 *
 * @author Дмитрий
 */
public interface ProductBusinessService {
    
    List<Product> getAllProducts() throws GeneralException;
    Product getProductById(String prodId) throws GeneralException;
    List<Product> getProductsByName(String name) throws GeneralException;
    List<Product> getProducts(String catId, String mkId, String name, String fromPrice, String toPrice) throws GeneralException;
    UserGoodMessage addProduct(Product product) throws GeneralException;
    UserGoodMessage updateProduct(Product product) throws GeneralException;
    UserGoodMessage deleteProduct(String prodId) throws GeneralException;
    
}
