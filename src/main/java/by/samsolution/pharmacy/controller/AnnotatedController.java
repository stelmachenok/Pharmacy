package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import by.samsolution.pharmacy.formvalidator.MedicamentValidator;
import by.samsolution.pharmacy.servlet.MyAppServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnnotatedController {

    Service service;
    MedicamentValidator medicamentValidator;
    private static Logger logger = LoggerFactory.getLogger(MyAppServletContextListener.class);

    @Autowired
    public AnnotatedController(Service service, MedicamentValidator medicamentValidator) {
        this.service = service;
        this.medicamentValidator = medicamentValidator;
    }

    @RequestMapping(value = "/formExecute", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("medicament") MedicamentDto medicamentDto,
            BindingResult result, ModelMap model){

        medicamentValidator.validate(medicamentDto, result);
        if (!result.hasErrors()) {
            try {
                service.addMedicament(medicamentDto);
                logger.info("Medicament " + medicamentDto + " pushed successfully");
            } catch (EntityAlreadyExistException | ObjectValidationFailedException e) {
                model.addAttribute("exceptionText", e.getMessage());
                logger.error(e.getMessage());
            }
        }
        model.addAttribute("medicaments", service.getAllMedicaments());
        return "medicaments";
    }

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {
        model.addAttribute("medicaments", service.getAllMedicaments());
        model.addAttribute("medicament", new MedicamentDto());
        return "medicaments";
    }
}
