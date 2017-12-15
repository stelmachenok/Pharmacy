package by.samsolution.pharmacy.dao;

import by.samsolution.pharmacy.dto.SearchDto;

import java.util.List;

public interface SearchDao {
    List<SearchDto> search(String request);
}
