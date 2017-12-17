package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(ModelMap model,
                                @RequestParam(value = "error", required = false) Boolean error,
                                @RequestParam(value = "logout", required = false) Boolean logout
    ) {
        if (error != null) {
            String errorMessage = message.getMessage("message.loginError", null, LocaleContextHolder.getLocale());
            model.addAttribute("error", errorMessage);
            logger.error("Login error");
        }
        if (logout != null) {
            String errorMessage = message.getMessage("message.logout", null, LocaleContextHolder.getLocale());
            model.addAttribute("error", errorMessage);
            logger.info("Logout");
        }
        User user = new User("", "");
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public String error403(){
        return "error403";
    }
}
