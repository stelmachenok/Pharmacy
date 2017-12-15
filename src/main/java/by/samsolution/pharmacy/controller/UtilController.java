package by.samsolution.pharmacy.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UtilController {

    @DateTimeFormat
    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    public Date getTime() {
        return new Date();
    }
}
