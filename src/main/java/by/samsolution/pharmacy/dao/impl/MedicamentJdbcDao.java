package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
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
        String SQL = "SELECT * FROM medicament ORDER BY " + request.getSortField().getFieldName() + (!request.getDirection() ? " DESC" : "");
        Map namedParameters = new HashMap();
        List<MedicamentEntity> medicaments = namedParameterJdbcTemplate.query(SQL, namedParameters, mapper);
        int from = request.getFrom();
        int size = request.getSize();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<MedicamentEntity> wantedMedicaments = new ArrayList<>();
        for (int i = from; i <= last; i++) {
            wantedMedicaments.add(medicaments.get(i));
        }
        return wantedMedicaments;
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
        return getAll().size();
    }

    @Override
    public int countOf(MedicamentsSearchRequest request) {
        return 0;
    }

    @Override
    public void update(MedicamentEntity entity) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "UPDATE Medicament SET " +
                "brandName = :brandName, " +
                "activeIngredient = :activeIngredient, " +
                "dosage = :dosage, " +
                "packingForm = :packingForm,  " +
                "internationalNonproprietaryName = :internationalNonproprietaryName, " +
                "releaseForm = :releaseForm, " +
                "guid = :guid, " +
                "medicamentCategory = :medicamentCategory " +
                "WHERE id = :id";
        Map namedParameters = new HashMap();
        namedParameters.put("brandName", entity.getBrandName());
        namedParameters.put("activeIngredient", entity.getActiveIngredient());
        namedParameters.put("dosage", entity.getDosage());
        namedParameters.put("packingForm", String.valueOf(entity.getPackingForm()));
        namedParameters.put("internationalNonproprietaryName", entity.getInternationalNonproprietaryName());
        namedParameters.put("releaseForm", String.valueOf(entity.getReleaseForm()));
        namedParameters.put("guid", String.valueOf(entity.getGuid()));
        namedParameters.put("id", String.valueOf(entity.getId()));
        namedParameters.put("medicamentCategory", entity.getCategory().getId());
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Updated more or less than 1 record!");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {
        String SQL = "DELETE FROM Medicament WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);
        if (changedRecords != 1) {
            throw new JdbcManipulationException("Deleted more or less than 1 record!");
        }
    }

    @Override
    public void create(MedicamentEntity entity) throws JdbcManipulationException {
        String SQL = "INSERT INTO medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, guid, medicamentCategory)" +
                " VALUES (:brandName, :activeIngredient, :dosage, :packingForm, :internationalNonproprietaryName, :releaseForm, :guid, :medicamentCategory)";
        Map namedParameters = new HashMap();
        namedParameters.put("brandName", entity.getBrandName());
        namedParameters.put("activeIngredient", entity.getActiveIngredient());
        namedParameters.put("dosage", entity.getDosage());
        namedParameters.put("packingForm", String.valueOf(entity.getPackingForm()));
        namedParameters.put("internationalNonproprietaryName", entity.getInternationalNonproprietaryName());
        namedParameters.put("releaseForm", String.valueOf(entity.getReleaseForm()));
        namedParameters.put("guid", String.valueOf(entity.getGuid()));
        namedParameters.put("medicamentCategory", entity.getCategory().getId());
        Integer changedRecords = namedParameterJdbcTemplate.update(SQL, namedParameters);

        if (changedRecords != 1) {
            throw new JdbcManipulationException("Created more or less than 1 record!");
        }
    }
}
