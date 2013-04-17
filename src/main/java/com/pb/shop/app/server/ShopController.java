/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.app.server.business.service.CategoryBusinessService;
import com.pb.shop.app.server.business.service.ImageBusinessService;
import com.pb.shop.app.server.business.service.MakerBusinessService;
import com.pb.shop.app.server.business.service.ProductBusinessService;
import com.pb.shop.app.server.dao.service.CategoryDaoService;
import com.pb.shop.app.server.dao.service.ProductDaoService;
import com.pb.shop.app.server.dao.service.MakerDaoService;
import com.pb.shop.app.server.dao.service.ImageService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Category;
import com.pb.shop.model.CategoryList;
import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import com.pb.shop.model.UserBadMessage;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Madness
 */
@Controller
public class ShopController {
    
    @Autowired
    CategoryBusinessService categoryBusinessService;
    @Autowired
    ImageBusinessService imageBusinessService;
    @Autowired
    MakerBusinessService makerBusinessService;
    @Autowired
    ProductBusinessService productBusinessService;

    public ShopController() {
    }


    @RequestMapping(value = "/makers/", method = RequestMethod.GET)
    @ResponseBody
    public MakersList getAllMakers() throws GeneralException {
        List<Maker> makers = makerBusinessService.getAllMakers();
        MakersList list = new MakersList(makers);
        return list;
    }

    @RequestMapping(value = "/maker/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Maker getMakerById(@PathVariable String id) throws GeneralException {
        Maker maker = makerBusinessService.getMakerById(id);
        return maker;
    }

    @RequestMapping(value = "/maker/by/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public MakersList getMakersByName(@PathVariable String name) throws GeneralException {
        List<Maker> makers = makerBusinessService.getMakersByName(name);
        MakersList list = new MakersList(makers);
        return list;
    }

    @RequestMapping(value = "/add/maker", method = RequestMethod.POST)
    @ResponseBody
    public void addMaker(@RequestBody Maker m) throws GeneralException {
        makerBusinessService.addMaker(m);
    }

    @RequestMapping(value = "/update/maker", method = RequestMethod.POST)
    @ResponseBody
    public void updateMaker(@RequestBody Maker m) throws GeneralException {
        makerBusinessService.updateMaker(m);
    }

    @RequestMapping(value = "/delete/maker/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteMaker(@PathVariable String id) throws GeneralException{
        makerBusinessService.deletMaker(id);
    }

    @RequestMapping(value = "/categoryes/", method = RequestMethod.GET)
    @ResponseBody
    public CategoryList getALLCategoryes() throws GeneralException {
        List<Category> category = categoryBusinessService.getAllCategories();
        CategoryList list = new CategoryList(category);
        return list;
    }

    @RequestMapping(value = "/category/by/id/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable String id) throws GeneralException {
        Category category = categoryBusinessService.getCategoryById(id);
        return category;
    }

    @RequestMapping(value = "/category/by/name/{name}")
    @ResponseBody
    public CategoryList getCategoryByName(@PathVariable String name) throws GeneralException {
        List<Category> categoryes = categoryBusinessService.getCategoriesByName(name);
        CategoryList list = new CategoryList(categoryes);
        return list;
    }

    @RequestMapping(value = "/add/category", method = RequestMethod.POST)
    @ResponseBody
    public void addCategory(@RequestBody Category c) throws GeneralException {
        categoryBusinessService.addCategory(c);
    }

    @RequestMapping(value = "/update/category", method = RequestMethod.POST)
    @ResponseBody
    public void updateCategory(@RequestBody Category c) throws GeneralException {
        categoryBusinessService.updateCategory(c);
    }

    @RequestMapping(value = "/delete/category/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteCategory(@PathVariable String id) throws GeneralException{
        categoryBusinessService.deleteCategory(id);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    @ResponseBody
    public ProductsList getAllProducts() throws GeneralException {
        List<Product> products = productBusinessService.getAllProducts();
        ProductsList list = new ProductsList(products);
        return list;
    }

    @RequestMapping(value = "/product/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable String id) throws GeneralException {
        return productBusinessService.getProductById(id);
    }

//    @RequestMapping(value = "/product/by/name/{name}", method = RequestMethod.GET)
//    @ResponseBody
//    public ProductsList getProductByName(@PathVariable String name) throws GeneralException {
//        List<Product> products = productBusinessService.getProductsByName(name);
//        ProductsList list = new ProductsList(products);
//        return list;
//    }
    
    @RequestMapping(value = "/product/by/name", method = RequestMethod.GET)
    @ResponseBody
    public ProductsList getProductByName(@RequestParam("name") String name) throws GeneralException {
        List<Product> products = productBusinessService.getProductsByName(name);
        ProductsList list = new ProductsList(products);
        return list;
    }
    
    @RequestMapping(value = "/products/params")
    @ResponseBody
    public ProductsList getProducts(@RequestParam(value = "catId",  required = false) String catId,@RequestParam(value = "mkId", required = false)  String mkId,
            @RequestParam(value = "name", required = false) String name,@RequestParam(value = "fromPrice", required = false) String fromPrice,
            @RequestParam(value = "toPrice", required = false) String toPrice) throws GeneralException{
        
        List<Product> products = productBusinessService.getProducts(catId, mkId, name, fromPrice, toPrice);
        ProductsList list = new ProductsList(products);
        return list;
        
    }

    @RequestMapping(value = "/add/product", method = RequestMethod.POST)
    @ResponseBody
    public void addProduct(@RequestBody Product p) throws GeneralException {
        productBusinessService.addProduct(p);
    }

    @RequestMapping(value = "/update/product", method = RequestMethod.POST)
    @ResponseBody
    public void updateProduct(@RequestBody Product p) throws GeneralException {
        productBusinessService.updateProduct(p);
    }

    @RequestMapping(value = "/delete/product/by/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteProduct(@PathVariable String ig) throws GeneralException{
        productBusinessService.deleteProduct(ig);
    }
    
    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public UserBadMessage handleIOException(GeneralException exception) {
        UserBadMessage ue = new UserBadMessage();
        ue.setMessage(exception.getMessage());
        return ue;
    }
    
    @RequestMapping("/image/{id}.jpg")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) throws IOException, GeneralException {

        InputStream in = imageBusinessService.getImageStreamById(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/add/image/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void addProductImage(@PathVariable String id, @RequestBody String imageDataString) throws GeneralException{
        imageBusinessService.saveImageById(id, imageDataString);
    }
}
