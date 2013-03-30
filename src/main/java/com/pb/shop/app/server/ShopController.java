/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.app.server;

import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import com.pb.shop.model.Product;
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
    public ResponseEntity<Void> test(){
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
    
    
    //Тест на отлавливание исключений
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Product handleIOException(Exception exception) {
        Product p = new Product(134);
        p.setProdDescription(exception.getMessage());
        return p;
    }
    
    @RequestMapping(value = "/test")
    public ModelAndView getTestEx() throws Exception{
        throw new Exception("Hello Exception!!");
    }
    //--------------------------------------------
}
