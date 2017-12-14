package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dto.SearchDto;
import by.samsolution.pharmacy.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private SearchService searchService;
    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public SearchDto[] getSearchResult(@RequestParam("request") String request){
        SearchDto[] searchDtos = null;
//        searchService.getSearchRequestResult(request);
        return searchDtos;
    }
}
