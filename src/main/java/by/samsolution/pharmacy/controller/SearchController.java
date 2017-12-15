package by.samsolution.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @RequestMapping(value = "/searchPage", method = RequestMethod.GET)
    public String showSearchPage(){
        return "searchPage";
    }
}
