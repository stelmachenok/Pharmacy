package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.dto.UserDto;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.formvalidator.AvailabilityValidator;
import by.samsolution.pharmacy.searchrequest.AvailabilitySearchFieldEnum;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import by.samsolution.pharmacy.service.AvailabilityService;
import by.samsolution.pharmacy.service.MedicamentService;
import by.samsolution.pharmacy.service.PharmacyService;
import by.samsolution.pharmacy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import static by.samsolution.pharmacy.searchrequest.AvailabilitySearchFieldEnum.MEDICAMENT_ID;


@Controller
public class AvailabilityOfDrugsController {
    private static Logger logger = LoggerFactory.getLogger(AvailabilityOfDrugsController.class);
    private MedicamentService medicamentService;
    private PharmacyService pharmacyService;
    private AvailabilityService availabilityService;
    private UserService userService;
    private AvailabilityValidator availabilityValidator;

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;

    @Autowired
    public AvailabilityOfDrugsController(MedicamentService medicamentService, PharmacyService pharmacyService, UserService userService, AvailabilityService availabilityService, AvailabilityValidator availabilityValidator) {
        this.medicamentService = medicamentService;
        this.pharmacyService = pharmacyService;
        this.userService = userService;
        this.availabilityService = availabilityService;
        this.availabilityValidator = availabilityValidator;
    }

    @RequestMapping(value = "/availabilityFormExecute", method = RequestMethod.POST)
    public String submit(@ModelAttribute("availability") AvailabilityDto availabilityDto,
                         BindingResult result,
                         ModelMap model,
                         Authentication authentication,
                         @RequestParam("sortField") AvailabilitySearchFieldEnum sortField,
                         @RequestParam("sortDir") Boolean sortDir,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("pageSize") Integer pageSize) {
        availabilityValidator.validate(availabilityDto, result);
        availabilityDto.setLastUpdate(new Date());
        if (!result.hasErrors()) {
            try {
                availabilityService.add(availabilityDto);
            } catch (EntityAlreadyExistException | EntityNotFoundException | DuplicatePrimaryKeyException e) {
                String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                model.addAttribute("errorText", info);
                logger.info(e.getMessage());
                addAttributes(model, authentication, pageNum, pageSize, sortField, sortDir);
                return "availabilityOfDrugs";
            }
            return "redirect:/availabilityOfDrugs";
        }
        addAttributes(model, authentication, pageNum, pageSize, sortField, sortDir);
        return "availabilityOfDrugs";
    }

    @RequestMapping(value = "/availabilityOfDrugs", method = RequestMethod.GET)
    public String availabilityOfDrugs(ModelMap model,
                                      Authentication authentication,
                                      @RequestParam(value = "page-num", required = false) Integer pageNum,
                                      @RequestParam(value = "page-size", required = false) Integer pageSize,
                                      @RequestParam(value = "sort-field", required = false) AvailabilitySearchFieldEnum sortField,
                                      @RequestParam(value = "sort-direction", required = false) Boolean sortDir,
                                      @RequestParam(value = "action", required = false) String action,
                                      @RequestParam(value = "id", required = false) Long id) {

        if (action != null && action.equals("delete") && id != null) {
            try {
                availabilityService.delete(id);
                logger.info("Record deleted successfully");
            } catch (EntityNotFoundException e) {
                String info = message.getMessage("message." + e.getClass().getSimpleName(), null, LocaleContextHolder.getLocale());
                model.addAttribute("errorText", info);
                logger.info(e.getMessage());
            }
        }
        addPharmacyIdAttribute(model, authentication);
        addAvailabilityAttribute(model);
        addAttributes(model, authentication, pageNum, pageSize, sortField, sortDir);
        return "availabilityOfDrugs";
    }

    private void addAttributes(ModelMap model, Authentication authentication, Integer pageNum, Integer pageSize, AvailabilitySearchFieldEnum sortField, Boolean sortDir) {
        addMedicamentsAttribute(model);
        addPharmacyNameAttribute(model, authentication);
        addRefAttributes(model, authentication, pageNum, pageSize, sortField, sortDir);
    }

    private void addPharmacyIdAttribute(ModelMap model, Authentication authentication) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        model.addAttribute("pharmacyId", pharmacyService.getById(userDto.getPharmacyId()).getId());
    }

    private void addPharmacyNameAttribute(ModelMap model, Authentication authentication) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        model.addAttribute("pharmacyName", pharmacyService.getById(userDto.getPharmacyId()).getPharmacyName());
    }

    private void addMedicamentsAttribute(ModelMap model) {
        List<MedicamentDto> medicaments = medicamentService.getAll();
        model.addAttribute("medicaments", medicaments);
    }

    private void addAvailabilityAttribute(ModelMap model) {
        AvailabilityDto availabilityDto = new AvailabilityDto();
        model.addAttribute("availability", availabilityDto);
    }

    private void addRefAttributes(ModelMap model, Authentication authentication, Integer pageNum, Integer pageSize, AvailabilitySearchFieldEnum sortField, Boolean sortDir) {
        AvailabilitySearchRequest request = new AvailabilitySearchRequest();
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 10;
        if (sortField != null) {
            request.setSortField(sortField);
        } else {
            request.setSortField(MEDICAMENT_ID);
            sortField = MEDICAMENT_ID;
        }
        if (sortDir != null) {
            request.setDirection(sortDir);
        } else {
            request.setDirection(true);
            sortDir = true;
        }

        AvailabilitySearchRequest availabilitySizeRequest = getAvailabilitySizeRequest(authentication);
        int recordsCount = availabilityService.countOf(availabilitySizeRequest);
        int pagesCount = (recordsCount % pageSize == 0) ? recordsCount / pageSize : recordsCount / pageSize + 1;
        int firstRecord = (pageNum - 1) * pageSize;
        int recordsOnPage = pageSize;
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        request.setFrom(firstRecord);
        request.setSize(recordsOnPage);
        addAvailabilitiesAttribute(model, authentication, request);
    }

    private void addAvailabilitiesAttribute(ModelMap model, Authentication authentication, AvailabilitySearchRequest availabilityRequest) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        availabilityRequest.setPharmacyId(userDto.getPharmacyId());
        List<AvailabilityDto> availabilities = availabilityService.getAll(availabilityRequest);
        availabilities.forEach((a) ->{
            a.setBrandName(medicamentService.getById(a.getMedicamentId()).getLabel());
        });
        model.addAttribute("availabilities", availabilities);
    }

    private AvailabilitySearchRequest getAvailabilitySizeRequest(Authentication authentication) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        AvailabilitySearchRequest availabilityRequest = new AvailabilitySearchRequest();
        availabilityRequest.setPharmacyId(userDto.getPharmacyId());
        return availabilityRequest;
    }

    private Long getPharmacyId(Authentication authentication) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        return userDto.getPharmacyId();
    }
}