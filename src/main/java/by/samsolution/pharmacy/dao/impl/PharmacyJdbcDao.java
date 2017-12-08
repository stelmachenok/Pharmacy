package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.entity.PharmacyCategory;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String SQL = "SELECT * FROM pharmacy ORDER BY " + request.getSortField().getFieldName() + (!request.getDirection() ? " DESC" : "");
        Map namedParameters = new HashMap();
        List<Pharmacy> pharmacies = namedParameterJdbcTemplate.query(SQL, namedParameters, mapper);
        int from = request.getFrom();
        int size = request.getSize();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<Pharmacy> wantedPharmacies = new ArrayList<>();
        for (int i = from; i <= last; i++) {
            wantedPharmacies.add(pharmacies.get(i));
        }
        return wantedPharmacies;
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
        return getAll().size();
    }

    @Override
    public void update(Pharmacy entity) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "UPDATE pharmacy SET " +
                "pharmacyName = :pharmacyName, " +
                "address = :address, " +
                "pharmacistName = :pharmacistName, " +
                "contactNumber = :contactNumber,  " +
                "login = :login, " +
                "password = :password, " +
                "category = :category, " +
                "uuid = :uuid " +
                "WHERE id = :id";
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
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Updated more or less than 1 record!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "DELETE FROM pharmacy WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Deleted more or less than 1 record!");
        }
    }

    @Override
    public void create(Pharmacy entity) throws JdbcManipulationException {
        String SQL = "INSERT INTO pharmacy (pharmacyName, address, pharmacistName, contactNumber, login, password, category, uuid)" +
        "VALUES (:pharmacyName, :address, :pharmacistName, :contactNumber, :login, :password, :category, :uuid)";
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
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Created more or less than 1 record!");
        }
    }
}
