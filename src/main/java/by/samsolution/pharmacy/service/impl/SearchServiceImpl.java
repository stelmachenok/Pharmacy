package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.PackingFormConverter;
import by.samsolution.pharmacy.dao.SearchDao;
import by.samsolution.pharmacy.dto.SearchDto;
import by.samsolution.pharmacy.service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService {
    private SearchDao searchDao;
    private PackingFormConverter packingFormConverter;

    public SearchServiceImpl(SearchDao searchDao, PackingFormConverter packingFormConverter) {
        this.searchDao = searchDao;
        this.packingFormConverter = packingFormConverter;
    }

    @Override
    public List<SearchDto> search(String request) {
        List<SearchDto> searchDtos = searchDao.search(request);
        searchDtos.forEach((s) -> s.setPackingFormName(packingFormConverter.entityToDto(s.getPackingForm()).getTranslatedName()));
        return searchDtos;
    }
}
