package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.util.SqlQueryReader;
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
import java.util.UUID;

public class MedicamentJdbcDao implements InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MedicamentCategoryJdbcDao categoryJdbcDao;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    class Mapper implements RowMapper<MedicamentEntity> {

        @Override
        public MedicamentEntity mapRow(ResultSet rs, int i) throws SQLException {
            MedicamentEntity medicamentEntity = new MedicamentEntity();
            medicamentEntity.setBrandName(rs.getString("brandName"));
            medicamentEntity.setActiveIngredient(rs.getString("activeIngredient"));
            medicamentEntity.setDosage(rs.getDouble("dosage"));
            medicamentEntity.setPackingForm(PackingForm.valueOf(rs.getString("packingForm")));
            medicamentEntity.setInternationalNonproprietaryName(rs.getString("internationalNonproprietaryName"));
            medicamentEntity.setReleaseForm(ReleaseForm.valueOf(rs.getString("releaseForm")));
            medicamentEntity.setCategory(categoryJdbcDao.getEntityById(rs.getLong("medicamentCategory")));
            medicamentEntity.setGuid(UUID.fromString(rs.getString("guid")));
            medicamentEntity.setId(rs.getLong("id"));
            return medicamentEntity;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<MedicamentEntity> getAll() {
        String SQL = "SELECT * FROM medicament";
        return namedParameterJdbcTemplate.query(SQL, mapper);
    }

    @Override
    public List<MedicamentEntity> getAll(MedicamentsSearchRequest request) {
        String SQL = "SELECT * FROM medicament ";
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
    public MedicamentEntity getEntityById(Long id) {
        String SQL = "SELECT * FROM medicament WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, mapper);
    }

    @Override
    public List<MedicamentEntity> getEntityByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM medicament WHERE brandName = ?",
                mapper,
                name
        );
    }

    @Override
    public int countOf() {
        String SQL = "SELECT COUNT(*) FROM medicament";
        return jdbcTemplate.queryForObject(SQL, Integer.class);
    }

    @Override
    public int countOf(MedicamentsSearchRequest request) {
        return 0;
    }

    @Override
    public void update(MedicamentEntity entity) throws EntityNotFoundException {
        String SQL = SqlQueryReader.read("updateMedicamentEntity");
        Map namedParameters = new HashMap();
        namedParameters.put("brandName", entity.getBrandName());
        namedParameters.put("activeIngredient", entity.getActiveIngredient());
        namedParameters.put("dosage", entity.getDosage());
        namedParameters.put("packingForm", String.valueOf(entity.getPackingForm()));
        namedParameters.put("internationalNonproprietaryName", entity.getInternationalNonproprietaryName());
        namedParameters.put("releaseForm", String.valueOf(entity.getReleaseForm()));
        namedParameters.put("guid", String.valueOf(entity.getGuid()));
        namedParameters.put("id", String.valueOf(entity.getId()));
        if (entity.getCategory() != null) {
            namedParameters.put("medicamentCategory", entity.getCategory().getId());
        }
        else{
            namedParameters.put("medicamentCategory", null);
        }

        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        String SQL = "DELETE FROM Medicament WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
        SQL = "DELETE FROM availability WHERE medicamentId = :id";
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void create(MedicamentEntity entity) {
        String SQL = SqlQueryReader.read("createMedicamentEntity");
        Map namedParameters = new HashMap();
        namedParameters.put("brandName", entity.getBrandName());
        namedParameters.put("activeIngredient", entity.getActiveIngredient());
        namedParameters.put("dosage", entity.getDosage());
        namedParameters.put("packingForm", String.valueOf(entity.getPackingForm()));
        namedParameters.put("internationalNonproprietaryName", entity.getInternationalNonproprietaryName());
        namedParameters.put("releaseForm", String.valueOf(entity.getReleaseForm()));
        namedParameters.put("guid", String.valueOf(entity.getGuid()));
        if (entity.getCategory() != null) {
            namedParameters.put("medicamentCategory", entity.getCategory().getId());
        }
        else{
            namedParameters.put("medicamentCategory", null);
        }

        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }
}
