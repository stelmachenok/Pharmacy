package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

public class AvailabilityJdbcDao implements InterfaceDAO<AvailabilityEntity, Long, String, AvailabilitySearchRequest> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    class Mapper implements RowMapper<AvailabilityEntity> {

        @Override
        public AvailabilityEntity mapRow(ResultSet rs, int i) throws SQLException {
            AvailabilityEntity availabilityEntity = new AvailabilityEntity();
            availabilityEntity.setPharmacyId(rs.getLong("pharmacyId"));
            availabilityEntity.setMedicamentId(rs.getLong("medicamentId"));
            availabilityEntity.setCount(rs.getLong("count"));
            availabilityEntity.setLastUpdate(rs.getDate("lastUpdate"));
            return availabilityEntity;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<AvailabilityEntity> getAll() {
        return null;
    }

    @Override
    public List<AvailabilityEntity> getAll(AvailabilitySearchRequest request) {
        String SQL = "SELECT * FROM availability ";
        if (request.getPharmacyId() != null) {
            SQL += " WHERE pharmacyId=" + request.getPharmacyId();
        }
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
    public AvailabilityEntity getEntityById(Long id) {
        String SQL = "SELECT * FROM availability WHERE medicamentId=:id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, mapper);
    }

    @Override
    public List<AvailabilityEntity> getEntityByName(String name) {
        return null;
    }

    @Override
    public int countOf() {
        return 0;
    }

    @Override
    public int countOf(AvailabilitySearchRequest request) {
        String SQL = "SELECT COUNT(*) FROM availability ";
        if (request.getPharmacyId() != null) {
            SQL += " WHERE pharmacyId=" + request.getPharmacyId();
        }
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
        return jdbcTemplate.queryForObject(SQL, Integer.class);
    }

    @Override
    public void update(AvailabilityEntity entity) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "UPDATE availability SET count = :count, lastUpdate = :lastUpdate " +
                "WHERE pharmacyId = :pharmacyId AND medicamentId = :medicamentId";
        Map namedParameters = new HashMap();
        namedParameters.put("pharmacyId", entity.getPharmacyId());
        namedParameters.put("medicamentId", entity.getMedicamentId());
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Updated more or less than 1 record!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "DELETE FROM availability WHERE medicamentId = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Deleted more or less than 1 record!");
        }
    }

    @Override
    public void delete(AvailabilitySearchRequest request) throws JdbcManipulationException {
        String SQL = "DELETE FROM availability WHERE medicamentId = :medicamentId AND pharmacyId = :pharmacyId";
        Map namedParameters = new HashMap();
        namedParameters.put("medicamentId", request.getMedicamentId());
        namedParameters.put("pharmacyId", request.getPharmacyId());

        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Deleted more or less than 1 record!");
        }
    }

    @Override
    public void create(AvailabilityEntity entity) throws JdbcManipulationException {
        String SQL = "INSERT INTO availability (pharmacyId, medicamentId, count, lastUpdate) " +
                "VALUES (:pharmacyId, :medicamentId, :count, :lastUpdate)";
        Map namedParameters = new HashMap();
        namedParameters.put("pharmacyId", entity.getPharmacyId());
        namedParameters.put("medicamentId", entity.getMedicamentId());
        namedParameters.put("count", entity.getCount());
        namedParameters.put("lastUpdate", entity.getLastUpdate());
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Created more or less than 1 record!");
        }
    }
}
