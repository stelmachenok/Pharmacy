package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.SearchDao;
import by.samsolution.pharmacy.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchJdbcDaoImpl implements SearchDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<SearchDto> search(String request) {
        return null;
    }

    class Mapper implements RowMapper<SearchDto> {
        @Override
        public SearchDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            SearchDto searchDto = new SearchDto();
//            setSomething
//            user.setId(rs.getLong("id"));
//            user.setLogin(rs.getString("login"));
//            user.setPassword(rs.getString("password"));
//            user.setRole(UserRole.valueOf(rs.getString("role")));
//            user.setPharmacyId(rs.getLong("pharmacyId"));
//            user.setEnabled(rs.getBoolean("enabled"));
            return searchDto;
        }
    }
}
