package by.samsolution.pharmacy.controller;


import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by y50-70 on 03.11.2017.
 */
public class FormExecuteController implements org.springframework.web.servlet.mvc.Controller {

    private final Logger logger = LoggerFactory.getLogger(MyController.class);

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mvc = new ModelAndView("redirect:/medicaments");
        httpServletRequest.setCharacterEncoding("utf-8");
        Enumeration en = httpServletRequest.getParameterNames();
        String stringMedicament = "";
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            stringMedicament += " " + httpServletRequest.getParameter(name);
        }

        try {
            service.addMedicament(stringMedicament);
        } catch (EntityAlreadyExistException | NumberFormatException | ObjectValidationFailedException e) {
            mvc.getModel().put("exceptionText", e.getMessage());
        }
        mvc.getModel().put("medicaments", service.getAllMedicaments());
        return mvc;
    }
}
