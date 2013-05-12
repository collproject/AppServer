/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server.business.impl;

import com.pb.shop.app.server.business.service.ProductBusinessService;
import com.pb.shop.app.server.dao.service.ProductDaoService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Product;
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
public class ProductBusinessServiceImpl implements ProductBusinessService {
    
    @Autowired
    @Qualifier("productImpl")
    ProductDaoService productDaoService;
    
    @Override
    public List<Product> getAllProducts() throws GeneralException {
        try {
            List<Product> list = productDaoService.getAllProducts();
            if (list.isEmpty()) {
                throw new GeneralException("Нет ни одного продукта.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public Product getProductById(String prodId) throws GeneralException {
        try {
            Integer.parseInt(prodId);
            Product product = productDaoService.getProductById(prodId);
            if (product == null) {
                throw new GeneralException("Продукт с таким ИН отсутствует.");
            }
            return product;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public List<Product> getProductsByName(String name) throws GeneralException {
        try {
            List<Product> list = productDaoService.getProductsByName(name);
            if (list.isEmpty()) {
                throw new GeneralException("Продукт с таким именем отсутствует.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public List<Product> getProducts(String catId, String mkId, String name, String fromPrice, String toPrice) throws GeneralException {
        try {
            
            try {
                if (catId != null) {
                    Integer.parseInt(catId);
                }
            } catch (Exception e) {
                throw new GeneralException("ИН категории \"" + catId + "\" не коректен.");
            }
            
            try {
                if (mkId != null) {
                    Integer.parseInt(mkId);
                }
                
            } catch (Exception e) {
                throw new GeneralException("ИН производителя \"" + mkId + "\" не коректен!!!.");
            }
            
//            if (name == null) {
//                name = "";
//            }
            
            try {
                if (fromPrice != null) {
                    Double.parseDouble(fromPrice);
                }
                if (toPrice != null) {
                    Double.parseDouble(toPrice);
                }
                if (fromPrice != null && toPrice != null) {
                    if (Double.parseDouble(fromPrice) > Double.parseDouble(toPrice)) {
                        throw new GeneralException("Диапазон цены введен не корректно.");
                    }
                }
            } catch (Exception e) {
                throw new GeneralException("Диапазон цены введен не корректно.");
            }
            List<Product> list = productDaoService.getProducts(catId, mkId, name, fromPrice, toPrice);
            if (list.isEmpty()) {
                throw new GeneralException("Нет ни одного продукта.");
            }
            return list;
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public UserGoodMessage addProduct(Product product) throws GeneralException {
        try {
            productDaoService.addProduct(product);
            return new UserGoodMessage("Продукт успешно добавлен.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public UserGoodMessage updateProduct(Product product) throws GeneralException {
        try {
            productDaoService.updateProduct(product);
            return new UserGoodMessage("Продукт успешно обновлен.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
    
    @Override
    public UserGoodMessage deleteProduct(String prodId) throws GeneralException {
        try {
            Integer.parseInt(prodId);
            productDaoService.deleteProduct(prodId);
            return new UserGoodMessage("Продукт успешно удален.");
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
}
