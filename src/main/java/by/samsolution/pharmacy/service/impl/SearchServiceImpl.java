package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.dao.SearchDao;
import by.samsolution.pharmacy.dto.SearchDto;
import by.samsolution.pharmacy.service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService{
    private SearchDao searchDao;

    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public List<SearchDto> search(String request) {
        return null;
    }
}
