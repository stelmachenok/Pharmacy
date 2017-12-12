package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.dto.UserDto;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.formvalidator.AvailabilityValidator;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import by.samsolution.pharmacy.service.AvailabilityService;
import by.samsolution.pharmacy.service.MedicamentService;
import by.samsolution.pharmacy.service.PharmacyService;
import by.samsolution.pharmacy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class AvailabilityOfDrugsController {
    private static Logger logger = LoggerFactory.getLogger(AvailabilityOfDrugsController.class);
    private MedicamentService medicamentService;
    private PharmacyService pharmacyService;
    private AvailabilityService availabilityService;
    private UserService userService;
    private AvailabilityValidator availabilityValidator;

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
                         @RequestParam("action") Long action,
                         ModelMap model,
                         Authentication authentication) {
        availabilityValidator.validate(availabilityDto, result);
        availabilityDto.setLastUpdate(new Date());
        if (!result.hasErrors()) {
            try {
                availabilityService.add(availabilityDto);
            } catch (ObjectValidationFailedException | EntityAlreadyExistException | JdbcManipulationException | EntityNotFoundException e) {
                e.printStackTrace();
                addAttributes(model, authentication, action);
                return "availabilityOfDrugs";
            }
            return "redirect:/availabilityOfDrugs";
        }
        addAttributes(model, authentication, action);
        return "availabilityOfDrugs";
    }

    @RequestMapping(value = "/availabilityOfDrugs", method = RequestMethod.GET)
    public String availabilityOfDrugs(@RequestParam("action") Long action,
                                      ModelMap model,
                                      Authentication authentication) {

        addPharmacyIdAttribute(model, authentication);
        addAvailabilityAttribute(model);
        addAttributes(model, authentication, action);
        return "availabilityOfDrugs";
    }

    private void addAttributes(ModelMap model, Authentication authentication, Long action) {
        addAvailabilitiesAttribute(model, authentication);
        addMedicamentsAttribute(model);
        addPharmacyNameAttribute(model, authentication);
        model.addAttribute("selectedMedicamentDto", action);
    }

    private void addPharmacyIdAttribute(ModelMap model, Authentication authentication){
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        model.addAttribute("pharmacyId", pharmacyService.getById(userDto.getPharmacyId()).getId());
    }

    private void addPharmacyNameAttribute(ModelMap model, Authentication authentication){
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        model.addAttribute("pharmacyName", pharmacyService.getById(userDto.getPharmacyId()).getPharmacyName());
    }

    private void addAvailabilitiesAttribute(ModelMap model, Authentication authentication){
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        AvailabilitySearchRequest availabilityRequest = new AvailabilitySearchRequest();
        availabilityRequest.setPharmacyId(userDto.getPharmacyId());
        List<AvailabilityDto> availabilities = availabilityService.getAll(availabilityRequest);
        availabilities.forEach((a) -> a.setBrandName(medicamentService.getById(a.getMedicamentId()).getBrandName()));
        model.addAttribute("availabilities", availabilities);
    }

    private void addMedicamentsAttribute(ModelMap model){
        List<MedicamentDto> medicaments = medicamentService.getAll();
        model.addAttribute("medicaments", medicaments);
    }

    private void addAvailabilityAttribute(ModelMap model){
        AvailabilityDto availabilityDto = new AvailabilityDto();
        model.addAttribute("availability", availabilityDto);
    }
}