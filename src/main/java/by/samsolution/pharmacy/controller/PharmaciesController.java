package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.converter.impl.PharmacyCategoryConverter;
import by.samsolution.pharmacy.dto.PharmacyCategoryDto;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.entity.PharmacyCategory;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.formvalidator.PharmacyValidator;
import by.samsolution.pharmacy.searchrequest.PharmacySearchFieldEnum;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.service.PharmacyService;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.samsolution.pharmacy.entity.PharmacyCategory.FIRST;
import static by.samsolution.pharmacy.searchrequest.PharmacySearchFieldEnum.PHARMACY_NAME;

@Controller
public class PharmaciesController {
    private PharmacyService pharmacyService;
    private PharmacyValidator validator;
    private PharmacyCategoryConverter pharmacyCategoryConverter;
    private static Logger logger = LoggerFactory.getLogger(PharmaciesController.class);

    @Autowired
    public PharmaciesController(PharmacyService pharmacyService, PharmacyValidator validator, PharmacyCategoryConverter pharmacyCategoryConverter) {
        this.pharmacyService = pharmacyService;
        this.validator = validator;
        this.pharmacyCategoryConverter = pharmacyCategoryConverter;
    }

    @RequestMapping(value = "/pharmaciesFormExecute", method = RequestMethod.POST)
    public String submit(@ModelAttribute("pharmacy") PharmacyDto pharmacyDto,
                         BindingResult result, ModelMap model,
                         @RequestParam("sortField") PharmacySearchFieldEnum sortField,
                         @RequestParam("sortDir") Boolean sortDir,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam("action") String action) {
        validator.validate(pharmacyDto, result);
        Long id = pharmacyDto.getId();

        if (!result.hasErrors()) {
            try {
                if (action != null && action.equals("edit")) {
                    pharmacyService.update(pharmacyDto);
                    logger.info("Pharmacy " + pharmacyDto + " edited successfully");
                } else {
                    pharmacyService.add(pharmacyDto);
                    logger.info("Pharmacy " + pharmacyDto + " pushed successfully");
                }
            } catch (EntityAlreadyExistException | ObjectValidationFailedException | EntityNotFoundException e) {
                model.addAttribute("exceptionText", e.getMessage());
                logger.error(e.getMessage());
                addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action, id);
                return "pharmacies";
            } catch (JdbcManipulationException e) {
                logger.error("Updated more or less than 1 record!");
            }
            return "redirect:/pharmacies?page-num=" + pageNum +
                    "&page-size=" + pageSize +
                    "&sort-field=" + sortField +
                    "&sort-dir=" + sortDir;
        } else {
            addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action, id);
            return "pharmacies";
        }
    }


    @RequestMapping(value = "/pharmacies", method = RequestMethod.GET)
    public String showPharmacies(ModelMap model, @RequestParam(value = "page-num", required = false) Integer pageNum,
                                 @RequestParam(value = "page-size", required = false) Integer pageSize,
                                 @RequestParam(value = "sort-field", required = false) PharmacySearchFieldEnum sortField,
                                 @RequestParam(value = "sort-direction", required = false) Boolean sortDir,
                                 @RequestParam(value = "action", required = false) String action,
                                 @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("pharmacy", new Pharmacy("Большая аптека № 1", "проспект Независимости 58, Минск", "Нина Шоцкая", "8 017 331-06-87", "Pharmacy", "Pharmacy", FIRST));
        if (action != null && id != null) {
            if (action.equals("delete")) {
                try {
                    pharmacyService.delete(id);
                } catch (EntityNotFoundException | JdbcManipulationException e) {
                    logger.error(e.getMessage());
                    model.addAttribute("exceptionText", e.getMessage());
                }
            }
            if (action.equals("edit")) {
                PharmacyDto pharmacyDto = pharmacyService.getById(id);
                model.addAttribute("pharmacy", pharmacyDto);
            }
        }

        addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action, id);
        return "pharmacies";
    }

    private void addAllAttributes(Integer pageNum, Integer pageSize, ModelMap model, PharmacySearchFieldEnum sortField, Boolean sortDir, String action, Long id) {
        PharmacySearchRequest request = new PharmacySearchRequest();
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        if (sortField != null) {
            request.setSortField(sortField);
        } else {
            request.setSortField(PHARMACY_NAME);
            sortField = PHARMACY_NAME;
        }
        if (sortDir != null) {
            request.setDirection(sortDir);
        } else {
            request.setDirection(true);
            sortDir = true;
        }

        int recordsCount = pharmacyService.getAll().size();
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int lastRecord = (recordsCount - 1 < pageNum * pageSize - 1) ? recordsCount - 1 : pageNum * pageSize - 1;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("action", action);
        List<PharmacyCategoryDto> pharmacyCategoryDtoList = Arrays.stream(PharmacyCategory.values()).map((p) -> pharmacyCategoryConverter.entityToDto(p)).collect(Collectors.toList());
        model.addAttribute("pharmacyCategoryValues", pharmacyCategoryDtoList);

        request.setFrom(firstRecord);
        request.setSize(lastRecord - firstRecord);
        model.addAttribute("pharmacies", pharmacyService.getAll(request));
    }
}
