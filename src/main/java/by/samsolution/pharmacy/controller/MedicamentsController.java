package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.searchrequest.MedicineSearchFieldEnum;
import by.samsolution.pharmacy.service.MedicamentService;
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

import static by.samsolution.pharmacy.searchrequest.MedicineSearchFieldEnum.BRAND_NAME;

@Controller
public class MedicamentsController {

    private MedicamentService medicamentService;
    private MedicamentValidator medicamentValidator;
    private MedicamentsSearchRequest request;
    private static Logger logger = LoggerFactory.getLogger(MedicamentsController.class);

    @Autowired
    public MedicamentsController(MedicamentService service, MedicamentValidator medicamentValidator, MedicamentsSearchRequest request) {
        this.medicamentService = service;
        this.medicamentValidator = medicamentValidator;
        this.request = request;
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
            @RequestParam(value = "page-size", required = false) Integer pageSize,
            @RequestParam(value = "sort-field", required = false) MedicineSearchFieldEnum sortField,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "id", required = false) Long id) {

        medicamentValidator.validate(medicamentDto, result);
        if (!result.hasErrors()) {
            try {
                medicamentService.add(medicamentDto);
                logger.info("Medicament " + medicamentDto + " pushed successfully");
            } catch (EntityAlreadyExistException | ObjectValidationFailedException e) {
                model.addAttribute("exceptionText", e.getMessage());
                logger.error(e.getMessage());
                addAllAttributes(pageNum, pageSize, model, sortField, action, id);
                return "medicaments";
            }
            return "redirect:/medicaments?page-num=" + pageNum +
                    "&page-size=" + pageSize +
                    "&sort-field=" + sortField +
                    "&action=" + action +
                    "&id=" + id;
        } else {
            addAllAttributes(pageNum, pageSize, model, sortField, action, id);
            return "medicaments";
        }
    }

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public String showAddUserForm(ModelMap model,
                                  @RequestParam(value = "page-num", required = false) Integer pageNum,
                                  @RequestParam(value = "page-size", required = false) Integer pageSize,
                                  @RequestParam(value = "sort-field", required = false) MedicineSearchFieldEnum sortField,
                                  @RequestParam(value = "action", required = false) String action,
                                  @RequestParam(value = "id", required = false) Long id) {
        if (action != null && id != null){
            try {
                medicamentService.delete(id);
            } catch (EntityNotFoundException e) {
                model.addAttribute("exceptionText", e.getMessage());
            }
        }
        addAllAttributes(pageNum, pageSize, model, sortField, action, id);
        model.addAttribute("medicament", new MedicamentDto());
        return "medicaments";
    }

    private void addAllAttributes(Integer pageNum, Integer pageSize, ModelMap model, MedicineSearchFieldEnum sortField, String action, Long id) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        if (sortField != null) {
            request.setSortField(sortField);
        }
        else{
            request.setSortField(BRAND_NAME);
            sortField = BRAND_NAME;
        }
        int recordsCount = medicamentService.getAll().size();
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int lastRecord = (recordsCount - 1 < pageNum * pageSize - 1) ? recordsCount - 1 : pageNum * pageSize - 1;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("firstRecord", firstRecord);
        model.addAttribute("lastRecord", lastRecord);
        model.addAttribute("sortField", sortField);
        model.addAttribute("action", action);
        request.setFrom(firstRecord);
        request.setSize(lastRecord - firstRecord);
        model.addAttribute("medicaments", medicamentService.getAll(request));
    }
}
