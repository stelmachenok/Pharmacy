package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PharmaciesController {
    Service service;
    private static Logger logger = LoggerFactory.getLogger(PharmaciesController.class);

    @Autowired
    public PharmaciesController(Service service) {
        this.service = service;
    }

    @RequestMapping("/pharmacies")
    public String pharmacies() {
        return "pharmacies";
    }
}
