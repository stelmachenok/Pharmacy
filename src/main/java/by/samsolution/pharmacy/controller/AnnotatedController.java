package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
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

    @Autowired
    public AnnotatedController(Service service){
        this.service = service;
    }

    @RequestMapping(value = "/formExecute", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("medicament") MedicamentDto medicamentDto,
            BindingResult result, ModelMap model) throws EntityAlreadyExistException, ObjectValidationFailedException {

        try {
            service.addMedicament(medicamentDto);
        } catch (EntityAlreadyExistException | ObjectValidationFailedException e) {
            model.addAttribute("exceptionText", e.getMessage());
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
