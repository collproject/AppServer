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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

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

    public ShopController() {
    }

//    Тестовый мапинг с использованием ViewResolver
//    @RequestMapping(value = "/makers/")
//    public ModelAndView getAllMakers() {
//        List<Maker> makers = makerService.getAllMakers();
//        ModelAndView mav =
//                new ModelAndView("shopXmlView", "data", new MakersList(makers));
//        return mav;
//    }
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

    //Тест на отлавливание исключений
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Product handleIOException(Exception exception) {
        Product p = new Product(134);
        p.setProdDescription(exception.getMessage());
        return p;
    }

    @RequestMapping(value = "/test")
    public ModelAndView getTestEx() throws Exception {
        throw new Exception("Hello Exception!!");
    }
    //--------------------------------------------
    
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
    
    
    
}
