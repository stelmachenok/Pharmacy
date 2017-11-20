package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import by.samsolution.pharmacy.formvalidator.MedicamentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicamentsController {

    Service service;
    MedicamentValidator medicamentValidator;
    private static Logger logger = LoggerFactory.getLogger(MedicamentsController.class);

    @Autowired
    public MedicamentsController(Service service, MedicamentValidator medicamentValidator) {
        this.service = service;
        this.medicamentValidator = medicamentValidator;
    }

    @RequestMapping("/")
    public String home() {
        return "welcome";
    }

    @RequestMapping(value = "/formExecute", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("medicament") MedicamentDto medicamentDto,
            BindingResult result, ModelMap model,
            @RequestParam(value = "page-num", required = false) Integer pageNum,
            @RequestParam(value = "page-size", required = false) Integer pageSize) {

        medicamentValidator.validate(medicamentDto, result);
        if (!result.hasErrors()) {
            try {
                service.addMedicament(medicamentDto);
                logger.info("Medicament " + medicamentDto + " pushed successfully");
            } catch (EntityAlreadyExistException | ObjectValidationFailedException e) {
                model.addAttribute("exceptionText", e.getMessage());
                logger.error(e.getMessage());
                addAllAttributes(pageNum, pageSize, model);
                return "medicaments";
            }
            return "redirect:/medicaments?page-num=" + pageNum + "&page-size=" + pageSize;
        } else {
            addAllAttributes(pageNum, pageSize, model);
            return "medicaments";
        }
    }

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public String showAddUserForm(ModelMap model,
                                  @RequestParam(value = "page-num", required = false) Integer pageNum,
                                  @RequestParam(value = "page-size", required = false) Integer pageSize) {
        addAllAttributes(pageNum, pageSize, model);
        model.addAttribute("medicament", new MedicamentDto());
        return "medicaments";
    }

    private void addAllAttributes(Integer pageNum, Integer pageSize, ModelMap model) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        int recordsCount = service.getAllMedicaments().size();
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int lastRecord = (recordsCount - 1 < pageNum * pageSize - 1) ? recordsCount - 1 : pageNum * pageSize - 1;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("firstRecord", firstRecord);
        model.addAttribute("lastRecord", lastRecord);
        model.addAttribute("medicaments", service.getAllMedicaments());
    }
}
