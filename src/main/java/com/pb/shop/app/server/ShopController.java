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
import com.pb.shop.model.UserGoodMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
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
    
    private static final Logger logger = Logger.getLogger(ShopController.class);

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
    public UserGoodMessage addMaker(@RequestBody Maker m) throws GeneralException {
        return makerBusinessService.addMaker(m);
    }

    @RequestMapping(value = "/update/maker", method = RequestMethod.POST)
    @ResponseBody
    public UserGoodMessage updateMaker(@RequestBody Maker m) throws GeneralException {
        return makerBusinessService.updateMaker(m);
    }

    @RequestMapping(value = "/delete/maker/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGoodMessage deleteMaker(@PathVariable String id) throws GeneralException{
        return makerBusinessService.deletMaker(id);
    }

    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    @ResponseBody
    public CategoryList getALLCategories() throws GeneralException {
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
    public UserGoodMessage addCategory(@RequestBody Category c) throws GeneralException {
        return categoryBusinessService.addCategory(c);
    }

    @RequestMapping(value = "/update/category", method = RequestMethod.POST)
    @ResponseBody
    public UserGoodMessage updateCategory(@RequestBody Category c) throws GeneralException {
        return categoryBusinessService.updateCategory(c);
    }

    @RequestMapping(value = "/delete/category/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGoodMessage deleteCategory(@PathVariable String id) throws GeneralException{
        return categoryBusinessService.deleteCategory(id);
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
    public UserGoodMessage addProduct(@RequestBody Product p) throws GeneralException {
        return productBusinessService.addProduct(p);
    }

    @RequestMapping(value = "/update/product", method = RequestMethod.POST)
    @ResponseBody
    public UserGoodMessage updateProduct(@RequestBody Product p) throws GeneralException {
        return productBusinessService.updateProduct(p);
    }

    @RequestMapping(value = "/delete/product/by/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGoodMessage deleteProduct(@PathVariable String id) throws GeneralException{
        return productBusinessService.deleteProduct(id);
    }
    
    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public UserBadMessage handleIOException(GeneralException exception) {
        UserBadMessage ue = new UserBadMessage();
        ue.setMessage(exception.getMessage());
        logger.error(ue.getMessage());
        return ue;
    }
    
    @RequestMapping("/image/{id}.jpg")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) throws IOException, GeneralException {

        InputStream in = imageBusinessService.getImageStreamById(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        ResponseEntity re = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        in.close();
        return re;
    }
    
    @RequestMapping(value = "/add/image/{id}", method = RequestMethod.POST)
    @ResponseBody
    public UserGoodMessage addProductImage(@PathVariable String id, @RequestBody String imageDataString) throws GeneralException{
        return imageBusinessService.saveImageById(id, imageDataString);
    }
    
    @RequestMapping(value = "/delete/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserGoodMessage delProductImage(@PathVariable String id) throws GeneralException{
        return imageBusinessService.delImageById(id);
    }
}
