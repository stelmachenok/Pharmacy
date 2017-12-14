package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dto.SearchDto;

import java.util.List;

public interface SearchService {
    List<SearchDto> search(String request);
}
