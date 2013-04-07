/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Category;
import com.pb.shop.model.CategoryList;
import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import com.pb.shop.model.UserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Madness
 */
@Controller
public class ShopController {

    @Autowired
    @Qualifier("makerImpl")
    MakerDaoService makerService;
    @Autowired
    @Qualifier("productImpl")
    ProductDaoService productService;
    @Autowired
    @Qualifier("categoryImpl")
    CategoryDaoService categoryService;
    @Autowired
    @Qualifier("imgImpl")
    ImageService imageService;

    public ShopController() {
    }

    @RequestMapping(value = "/t", method = RequestMethod.GET)
    public ResponseEntity<Void> test() {
        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/makers/", method = RequestMethod.GET)
    @ResponseBody
    public MakersList getAllMakers() {
        List<Maker> makers = makerService.getAllMakers();
        MakersList list = new MakersList(makers);
        return list;
    }

    @RequestMapping(value = "/maker/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Maker getMakerById(@PathVariable String id) {
        Maker maker = makerService.getMakerById(id);
        return maker;
    }

    @RequestMapping(value = "/maker/by/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public MakersList getMakersByName(@PathVariable String name) {
        List<Maker> makers = makerService.getMakersByName(name);
        MakersList list = new MakersList(makers);
        return list;
    }

    @RequestMapping(value = "/admin/add/maker", method = RequestMethod.POST)
    @ResponseBody
    public void addMaker(@RequestBody Maker m) {
        makerService.addMaker(m);
    }

    @RequestMapping(value = "/admin/update/maker", method = RequestMethod.POST)
    @ResponseBody
    public void updateMaker(@RequestBody Maker m) {
        makerService.updateMaker(m);
    }

    @RequestMapping(value = "/delete/maker/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteMaker(@PathVariable String id){
        makerService.deletMaker(id);
    }

    @RequestMapping(value = "/categoryes/", method = RequestMethod.GET)
    @ResponseBody
    public CategoryList getALLCategoryes() {
        List<Category> category = categoryService.getAllCategories();
        CategoryList list = new CategoryList(category);
        return list;
    }

    @RequestMapping(value = "/category/by/id/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable String id) {
        Category category = categoryService.getCategoryById(id);
        return category;
    }

    @RequestMapping(value = "/category/by/name/{name}")
    @ResponseBody
    public CategoryList getCategoryByName(@PathVariable String name) {
        List<Category> categoryes = categoryService.getCategoriesByName(name);
        CategoryList list = new CategoryList(categoryes);
        return list;
    }

    @RequestMapping(value = "/add/category", method = RequestMethod.POST)
    @ResponseBody
    public void addCategory(@RequestBody Category c) {
        categoryService.addCategory(c);
    }

    @RequestMapping(value = "/update/category", method = RequestMethod.POST)
    @ResponseBody
    public void updateCategory(@RequestBody Category c) {
        categoryService.updateCategory(c);
    }

    @RequestMapping(value = "/delete/category/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    @ResponseBody
    public ProductsList getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ProductsList list = new ProductsList(products);
        return list;
    }

    @RequestMapping(value = "/product/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/product/by/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ProductsList getProductByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        ProductsList list = new ProductsList(products);
        return list;
    }

    @RequestMapping(value = "/add/product", method = RequestMethod.POST)
    @ResponseBody
    public void addProduct(@RequestBody Product p) {
        productService.addProduct(p);
    }

    @RequestMapping(value = "/update/product", method = RequestMethod.POST)
    @ResponseBody
    public void updateProduct(@RequestBody Product p) {
        productService.updateProduct(p);
    }

    @RequestMapping(value = "/delete/product/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteProduct(@PathVariable String ig){
        productService.deleteProduct(ig);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public UserException handleIOException(Exception exception) {
        UserException ue = new UserException();
    
        if (exception instanceof DuplicateKeyException) {
            ue.setMessage("Запись с таким ид уже существует");
        }
        if (exception instanceof CannotGetJdbcConnectionException) {
            ue.setMessage("Соединение с БД отсутствует");
        }
        if (exception instanceof IndexOutOfBoundsException) {
            ue.setMessage("Невозможно получить данные, запись с таким ид отсутствует");
        }
    
        if (exception instanceof DataIntegrityViolationException) {
            ue.setMessage("Невозможно обновить/удалить запись");
        } 
        else {
            ue.setMessage(exception.getClass().getName());
        }
        return ue;
    }
    
    @RequestMapping("/image/{id}.jpg")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) throws IOException {

        InputStream in = imageService.getImageStreamById(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/add/image/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addProductImage(@PathVariable String id, @RequestBody String imageDataString){
        imageService.saveImageById(id, imageDataString);
    }
    
}
