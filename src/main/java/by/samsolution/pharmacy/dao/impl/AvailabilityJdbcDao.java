package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String SQL = "SELECT * FROM availability";
        List<AvailabilityEntity> entities = namedParameterJdbcTemplate.query(SQL, mapper);

        return entities.stream().filter((u) -> ((request.getPharmacyId() == null || request.getPharmacyId() != null && u.getPharmacyId().equals(request.getPharmacyId())) &&
                (request.getMedicamentId() == null || request.getMedicamentId() != null && u.getMedicamentId().equals(request.getMedicamentId())) &&
                (request.getCount() == null || request.getCount() != null && u.getCount().equals(request.getCount())) &&
                (request.getLastUpdate() == null || request.getLastUpdate() != null && u.getLastUpdate().equals(request.getLastUpdate()))))
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityEntity getEntityById(Long id) {
        return null;
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
