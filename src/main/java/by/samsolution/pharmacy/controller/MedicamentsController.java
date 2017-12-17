package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.converter.impl.PackingFormConverter;
import by.samsolution.pharmacy.converter.impl.ReleaseFormConverter;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.dto.PackingFormDto;
import by.samsolution.pharmacy.dto.ReleaseFormDto;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.searchrequest.MedicineSearchFieldEnum;
import by.samsolution.pharmacy.service.CategoryService;
import by.samsolution.pharmacy.service.MedicamentService;
import by.samsolution.pharmacy.formvalidator.MedicamentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import static by.samsolution.pharmacy.entity.PackingForm.CAPSULE;
import static by.samsolution.pharmacy.entity.ReleaseForm.WITHOUT_RECIPE;
import static by.samsolution.pharmacy.searchrequest.MedicineSearchFieldEnum.BRAND_NAME;

@Controller
public class MedicamentsController {

    private MedicamentService medicamentService;
    private CategoryService categoryService;
    private MedicamentValidator medicamentValidator;
    private PackingFormConverter packingFormConverter;
    private ReleaseFormConverter releaseFormConverter;
    private static Logger logger = LoggerFactory.getLogger(MedicamentsController.class);

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;

    @Autowired
    public MedicamentsController(MedicamentService medicamentService, CategoryService categoryService, MedicamentValidator medicamentValidator, PackingFormConverter packingFormConverter, ReleaseFormConverter releaseFormConverter) {
        this.medicamentService = medicamentService;
        this.categoryService = categoryService;
        this.medicamentValidator = medicamentValidator;
        this.packingFormConverter = packingFormConverter;
        this.releaseFormConverter = releaseFormConverter;
    }

    @RequestMapping(value = "/formExecute", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("medicament") MedicamentDto medicamentDto,
            BindingResult result, ModelMap model,
            @RequestParam("sortField") MedicineSearchFieldEnum sortField,
            @RequestParam("sortDir") Boolean sortDir,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("action") String action) {
        medicamentValidator.validate(medicamentDto, result);
        CategoryDto categoryDto = categoryService.getById(medicamentDto.getCategoryDtoId());
        medicamentDto.setCategory(categoryDto);
        if (!result.hasErrors()) {
            try {
                if (action != null && action.equals("edit")) {
                    medicamentService.update(medicamentDto);
                    logger.info("Medicament " + medicamentDto + " edited successfully");
                } else {
                    medicamentService.add(medicamentDto);
                    logger.info("Medicament " + medicamentDto + " pushed successfully");
                }
            } catch (EntityAlreadyExistException | EntityNotFoundException | DuplicatePrimaryKeyException e) {
                String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                model.addAttribute("errorText", info);
                logger.info(e.getMessage());
                addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
                return "medicaments";
            }
            return "redirect:/medicaments?page-num=" + pageNum +
                    "&page-size=" + pageSize +
                    "&sort-field=" + sortField +
                    "&sort-dir=" + sortDir;
        } else {
            addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
            return "medicaments";
        }
    }

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public String showMedicaments(ModelMap model,
                                  @RequestParam(value = "page-num", required = false) Integer pageNum,
                                  @RequestParam(value = "page-size", required = false) Integer pageSize,
                                  @RequestParam(value = "sort-field", required = false) MedicineSearchFieldEnum sortField,
                                  @RequestParam(value = "sort-direction", required = false) Boolean sortDir,
                                  @RequestParam(value = "action", required = false) String action,
                                  @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("medicament", new MedicamentDto());
        if (action != null && action.equals("delete") && id != null) {
            try {
                medicamentService.delete(id);
                logger.info("Medicament deleted successfully");
            } catch (EntityNotFoundException e) {
                String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                model.addAttribute("errorText", info);
                logger.info(e.getMessage());
            }
        }
        if (action != null && action.equals("edit") && id != null) {
            MedicamentDto medicamentDto = medicamentService.getById(id);
            model.addAttribute("medicament", medicamentDto);
        }

        addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
        return "medicaments";
    }

    private void addAllAttributes(Integer pageNum, Integer pageSize, ModelMap model, MedicineSearchFieldEnum sortField, Boolean sortDir, String action) {
        MedicamentsSearchRequest request = new MedicamentsSearchRequest();
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        if (sortField != null) {
            request.setSortField(sortField);
        } else {
            request.setSortField(BRAND_NAME);
            sortField = BRAND_NAME;
        }
        if (sortDir != null) {
            request.setDirection(sortDir);
        } else {
            request.setDirection(true);
            sortDir = true;
        }

        int recordsCount = medicamentService.countOf();
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int recordsOnPage = pageSize;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("action", action);
        model.addAttribute("categories", categoryService.getAll());
        List<PackingFormDto> packingFormDtoList = Arrays.stream(PackingForm.values()).map((p) -> packingFormConverter.entityToDto(p)).collect(Collectors.toList());
        model.addAttribute("packingFormValues", packingFormDtoList);
        List<ReleaseFormDto> releaseFormDtoList = Arrays.stream(ReleaseForm.values()).map((p) -> releaseFormConverter.entityToDto(p)).collect(Collectors.toList());
        model.addAttribute("releaseFormValues", releaseFormDtoList);

        request.setFrom(firstRecord);
        request.setSize(recordsOnPage);
        model.addAttribute("medicaments", medicamentService.getAll(request));
    }
}
