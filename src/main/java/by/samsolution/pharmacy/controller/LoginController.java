package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(ModelMap model){
        User user = new User("", "");
        model.addAttribute("user", user);
        return "loginPage";
    }
}
