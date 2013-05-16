package com.pb.shop.app.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.pb.shop.app.server.business.service.CategoryBusinessService;
import com.pb.shop.app.server.business.service.ImageBusinessService;
import com.pb.shop.app.server.business.service.MakerBusinessService;
import com.pb.shop.app.server.business.service.ProductBusinessService;
import com.pb.shop.model.Maker;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author Дмитрий
 */
@Controller
public class WebPageController{
    private static final Logger logger = Logger.getLogger(WebPageController.class.getName());
    @Autowired
    CategoryBusinessService categoryBusinessService;
    @Autowired
    ImageBusinessService imageBusinessService;
    @Autowired
    MakerBusinessService makerBusinessService;
    @Autowired
    ProductBusinessService productBusinessService;
    
    public WebPageController() {
    }
    
    @RequestMapping(value = "/index.html")
    public String indexPage(Model model){
        model.addAttribute("maker", new Maker(12, "sfsdf"));
        logger.severe("controller invokation");
        return "default";
    }
}
