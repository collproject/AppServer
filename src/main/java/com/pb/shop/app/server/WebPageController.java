package com.pb.shop.app.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pb.shop.app.server.business.service.CategoryBusinessService;
import com.pb.shop.app.server.business.service.ImageBusinessService;
import com.pb.shop.app.server.business.service.MakerBusinessService;
import com.pb.shop.app.server.business.service.ProductBusinessService;
import com.pb.shop.exception.GeneralException;
import com.pb.shop.model.Category;
import com.pb.shop.model.Maker;
import com.pb.shop.model.Product;
import com.pb.shop.model.UserBadMessage;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Дмитрий
 */
@Controller
@RequestMapping(value = "/")
public class WebPageController {

    private static final Logger logger = Logger.getLogger(WebPageController.class.getName());
    @Autowired
    private CategoryBusinessService categoryBusinessService;
    @Autowired
    private ImageBusinessService imageBusinessService;
    @Autowired
    private MakerBusinessService makerBusinessService;
    @Autowired
    private ProductBusinessService productBusinessService;

    public WebPageController() {
    }

    @RequestMapping(value = "/index.html")
    public String indexPage(Model model) throws GeneralException {
        logger.info("Ingex page");
        List<Maker> makers = makerBusinessService.getAllMakers();
        List<Product> products = productBusinessService.getAllProducts();
        List<Category> categories = categoryBusinessService.getAllCategories();
        model.addAttribute("makers", makers);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }

    @ExceptionHandler(GeneralException.class)
    @ResponseBody
    public UserBadMessage handleIOException(GeneralException exception) {
        UserBadMessage ue = new UserBadMessage();
        ue.setMessage(exception.getMessage());
        logger.error(ue.getMessage());
        return ue;
    }
}
