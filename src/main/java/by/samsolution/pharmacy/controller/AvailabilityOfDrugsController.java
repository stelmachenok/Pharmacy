package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.UserDto;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import by.samsolution.pharmacy.service.MedicamentService;
import by.samsolution.pharmacy.service.PharmacyService;
import by.samsolution.pharmacy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AvailabilityOfDrugsController {
    private static Logger logger = LoggerFactory.getLogger(AvailabilityOfDrugsController.class);
    private MedicamentService medicamentService;
    private PharmacyService pharmacyService;
    private UserService userService;

    @Autowired
    public AvailabilityOfDrugsController(MedicamentService medicamentService, PharmacyService pharmacyService, UserService userService) {
        this.medicamentService = medicamentService;
        this.pharmacyService = pharmacyService;
        this.userService = userService;
    }

    @RequestMapping(value = "/availabilityOfDrugs", method = RequestMethod.GET)
    public String availabilityOfDrugs(ModelMap model,
                                      Authentication authentication) {
        String userName = authentication.getName();
        UserSearchRequest request = new UserSearchRequest();
        request.setLogin(userName);
//        UserDto userDto = userService.getAll().stream().filter((u)->u.getLogin().equals(userName)).findAny().get();
        UserDto userDto = userService.getAll(request).stream().findAny().get();
        model.addAttribute("pharmacyName", pharmacyService.getById(userDto.getPharmacyId()).getPharmacyName());
        model.addAttribute("availableMedicaments", pharmacyService.getById(userDto.getPharmacyId()).getPharmacyName());
        return "availabilityOfDrugs";
    }



}