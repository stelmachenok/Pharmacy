package by.samsolution.pharmacy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PharmaciesController {
    private static Logger logger = LoggerFactory.getLogger(PharmaciesController.class);

    public PharmaciesController(){
    }

    @RequestMapping("/pharmacies")
    public String pharmacies() {
        return "pharmacies";
    }
}
