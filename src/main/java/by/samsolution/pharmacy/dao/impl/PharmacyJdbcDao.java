package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.entity.PharmacyCategory;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.util.SqlQueryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PharmacyJdbcDao implements InterfaceDAO<Pharmacy, Long, String, PharmacySearchRequest> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    class Mapper implements RowMapper<Pharmacy> {
        @Override
        public Pharmacy mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setPharmacyName(rs.getString("pharmacyName"));
            pharmacy.setAddress(rs.getString("address"));
            pharmacy.setPharmacistName(rs.getString("pharmacistName"));
            pharmacy.setContactNumber(rs.getString("contactNumber"));
            pharmacy.setLogin(rs.getString("login"));
            pharmacy.setPassword(rs.getString("password"));
            pharmacy.setCategory(PharmacyCategory.valueOf(rs.getString("category")));
            pharmacy.setUuid(UUID.fromString(rs.getString("uuid")));
            pharmacy.setId(rs.getLong("id"));
            return pharmacy;
        }
    }

    private final Mapper mapper = new Mapper();


    @Override
    public List<Pharmacy> getAll() {
        String SQL = "SELECT * FROM pharmacy";
        return namedParameterJdbcTemplate.query(SQL, mapper);
    }

    @Override
    public List<Pharmacy> getAll(PharmacySearchRequest request) {
        String SQL = "SELECT * FROM pharmacy ";
        if (request.getSortField() != null) {
            SQL += " ORDER BY " + request.getSortField().getFieldName();
            if (request.getDirection() != null && !request.getDirection()) {
                SQL += " DESC ";
            }
        }
        if (request.getSize() != null) {
            SQL += " LIMIT " + request.getSize();
        }
        if (request.getFrom() != null) {
            SQL += " OFFSET " + request.getFrom();
        }
        return namedParameterJdbcTemplate.query(SQL, mapper);
    }

    @Override
    public Pharmacy getEntityById(Long id) {
        String SQL = "SELECT * FROM pharmacy WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, mapper);
    }

    @Override
    public List<Pharmacy> getEntityByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM pharmacy WHERE pharmacyName = ?",
                mapper,
                name
        );
    }

    @Override
    public int countOf() {
        String SQL = "SELECT COUNT(*) FROM pharmacy";
        return jdbcTemplate.queryForObject(SQL, Integer.class);
    }

    @Override
    public int countOf(PharmacySearchRequest request) {
        return 0;
    }

    @Override
    public void update(Pharmacy entity) throws EntityNotFoundException {
        String SQL = SqlQueryReader.read("updatePharmacy");
        Map namedParameters = new HashMap();
        namedParameters.put("pharmacyName", entity.getPharmacyName());
        namedParameters.put("address", entity.getAddress());
        namedParameters.put("pharmacistName", entity.getPharmacistName());
        namedParameters.put("contactNumber", entity.getContactNumber());
        namedParameters.put("login", entity.getLogin());
        namedParameters.put("password", entity.getPassword());
        namedParameters.put("category", String.valueOf(entity.getCategory()));
        namedParameters.put("uuid", String.valueOf(entity.getUuid()));
        namedParameters.put("id", entity.getId());
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        String SQL = "DELETE FROM pharmacy WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
        SQL = "DELETE FROM availability WHERE pharmacyId = :id";
        namedParameterJdbcTemplate.update(SQL, namedParameters);
        SQL = "DELETE FROM usertable WHERE pharmacyId = :id";
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void create(Pharmacy entity) throws DuplicatePrimaryKeyException {
        String SQL = SqlQueryReader.read("createPharmacy");
        Map namedParameters = new HashMap();
        namedParameters.put("pharmacyName", entity.getPharmacyName());
        namedParameters.put("address", entity.getAddress());
        namedParameters.put("pharmacistName", entity.getPharmacistName());
        namedParameters.put("contactNumber", entity.getContactNumber());
        namedParameters.put("login", entity.getLogin());
        namedParameters.put("password", entity.getPassword());
        namedParameters.put("category", String.valueOf(entity.getCategory()));
        namedParameters.put("uuid", String.valueOf(entity.getUuid()));
        namedParameters.put("id", entity.getId());
        namedParameterJdbcTemplate.update(SQL, namedParameters);
        SQL = "SELECT id FROM pharmacy WHERE pharmacyName = :pharmacyName AND address = :address";
        namedParameters = new HashMap();
        namedParameters.put("pharmacyName", entity.getPharmacyName());
        namedParameters.put("address", entity.getAddress());
        Long pharmacyId = namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, Long.class);
        SQL = "INSERT INTO usertable (login, password, role, pharmacyId, enabled)" +
                "VALUES (:login, :password, :role, :pharmacyId, :enabled)";
        namedParameters = new HashMap();
        namedParameters.put("login", entity.getLogin());
        namedParameters.put("password", entity.getPassword());
        namedParameters.put("role", "ROLE_PHARMACIST");
        namedParameters.put("pharmacyId", pharmacyId);
        namedParameters.put("enabled", true);
        try {
            namedParameterJdbcTemplate.update(SQL, namedParameters);
        }
        catch (DuplicateKeyException e){
            throw new DuplicatePrimaryKeyException(e);
        }
    }
}
