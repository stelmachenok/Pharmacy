package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.SearchDao;
import by.samsolution.pharmacy.dto.SearchDto;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.util.SqlQueryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchJdbcDaoImpl implements SearchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    class Mapper implements RowMapper<SearchDto> {
        @Override
        public SearchDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            SearchDto searchDto = new SearchDto();
            searchDto.setBrandName(rs.getString("brandName"));
            searchDto.setPackingForm(PackingForm.valueOf(rs.getString("packingForm")));
            searchDto.setActiveIngredient(rs.getString("activeIngredient"));
            searchDto.setCount(rs.getLong("count"));
            searchDto.setLastUpdate(rs.getDate("lastUpdate"));
            searchDto.setPharmacyName(rs.getString("pharmacyName"));
            searchDto.setAddress(rs.getString("address"));
            searchDto.setContactNumber(rs.getString("contactNumber"));
            searchDto.setPharmacyId(rs.getLong("pharmacyId"));
            searchDto.setMedicamentId(rs.getLong("medicamentId"));
            return searchDto;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<SearchDto> search(String request) {
        if (request == null || request.equals(""))
            return new ArrayList<>();
        String SQL = SqlQueryReader.read("InsertAvailability");
        Map namedParameters = new HashMap();
        namedParameters.put("request", "%" + request + "%");
        return namedParameterJdbcTemplate.query(SQL, namedParameters, mapper);
    }
}
