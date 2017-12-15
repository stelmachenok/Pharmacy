package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.SearchDto;
import by.samsolution.pharmacy.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchRestController {
    private SearchService searchService;
    private static Logger logger = LoggerFactory.getLogger(SearchRestController.class);

    @Autowired
    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SearchDto> getSearchResult(@RequestParam("request") String request){
        return searchService.search(request);
    }

}
