package by.samsolution.pharmacy.controller;

import java.io.IOException;
import java.util.Map;

import by.samsolution.pharmacy.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyController implements org.springframework.web.servlet.mvc.Controller {

    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mvc = new ModelAndView("medicaments");
        mvc.getModel().put("medicaments", service.getAllMedicaments());
        return mvc;
    }


}