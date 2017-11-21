package by.samsolution.pharmacy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoriesController {
    private static Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    public CategoriesController() {

    }

    @RequestMapping("/categories")
    public String categories() {
        return "categories";
    }
}