package by.samsolution.pharmacy.controller;

import java.io.IOException;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        mvc.getModel().put("medicament", new Medicament());
        return mvc;
    }


}