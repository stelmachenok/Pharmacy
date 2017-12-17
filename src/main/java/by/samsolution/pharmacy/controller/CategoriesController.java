package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.formvalidator.CategoryValidator;
import by.samsolution.pharmacy.searchrequest.CategorySearchFieldEnum;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.service.CategoryService;
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

import static by.samsolution.pharmacy.searchrequest.CategorySearchFieldEnum.CATEGORY_NAME;

@Controller
public class CategoriesController {

    private CategoryService categoryService;
    private CategoryValidator validator;
    private static Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;

    @Autowired
    public CategoriesController(CategoryService categoryService, CategoryValidator validator) {
        this.categoryService = categoryService;
        this.validator = validator;
    }

    @RequestMapping(value = "/categoryFormExecute", method = RequestMethod.POST)
    public String submit(@ModelAttribute("category") CategoryDto categoryDto,
                         BindingResult result, ModelMap model,
                         @RequestParam("sortField") CategorySearchFieldEnum sortField,
                         @RequestParam("sortDir") Boolean sortDir,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize") Integer pageSize,
                         @RequestParam("action") String action) {
        validator.validate(categoryDto, result);
        if (!result.hasErrors()) {
            try {
                if (action != null && action.equals("edit")) {
                    categoryService.update(categoryDto);
                    logger.info("Category " + categoryDto + " edited successfully");
                } else {
                    categoryService.add(categoryDto);
                    logger.info("Category " + categoryDto + " pushed successfully");
                }
            } catch (EntityAlreadyExistException | EntityNotFoundException | DuplicatePrimaryKeyException e) {
                String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                model.addAttribute("errorText", info);
                logger.info(e.getMessage());
                addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
                return "categories";
            }
            return "redirect:/categories?page-num=" + pageNum +
                    "&page-size=" + pageSize +
                    "&sort-field=" + sortField +
                    "&sort-dir=" + sortDir;
        } else {
            addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
            return "categories";
        }
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String showCategories(ModelMap model, @RequestParam(value = "page-num", required = false) Integer pageNum,
                                 @RequestParam(value = "page-size", required = false) Integer pageSize,
                                 @RequestParam(value = "sort-field", required = false) CategorySearchFieldEnum sortField,
                                 @RequestParam(value = "sort-direction", required = false) Boolean sortDir,
                                 @RequestParam(value = "action", required = false) String action,
                                 @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("category", new CategoryDto());
        if (action != null && id != null) {
            if (action.equals("delete")) {
                try {
                    categoryService.delete(id);
                    logger.info("Category deleted successfully");
                } catch (EntityNotFoundException e) {
                    String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                    model.addAttribute("errorText", info);
                    logger.info(e.getMessage());
                }
            }
            if (action.equals("edit")) {
                CategoryDto categoryDto = categoryService.getById(id);
                model.addAttribute("category", categoryDto);
            }
        }

        addAllAttributes(pageNum, pageSize, model, sortField, sortDir, action);
        return "categories";
    }

    private void addAllAttributes(Integer pageNum, Integer pageSize, ModelMap model, CategorySearchFieldEnum sortField, Boolean sortDir, String action) {
        CategorySearchRequest request = new CategorySearchRequest();
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        if (sortField != null) {
            request.setSortField(sortField);
        } else {
            request.setSortField(CATEGORY_NAME);
            sortField = CATEGORY_NAME;
        }
        if (sortDir != null) {
            request.setDirection(sortDir);
        } else {
            request.setDirection(true);
            sortDir = true;
        }

        int recordsCount = categoryService.countOf();
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int recordsOnPage = pageSize;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("action", action);

        request.setFrom(firstRecord);
        request.setSize(recordsOnPage);
        model.addAttribute("categories", categoryService.getAll(request));
    }

}

