package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MedicamentJdbcDao implements InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest>{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class Mapper implements RowMapper<MedicamentEntity>{

        @Override
        public MedicamentEntity mapRow(ResultSet rs, int i) throws SQLException {
            MedicamentEntity medicamentEntity = new MedicamentEntity();
            medicamentEntity.setBrandName(rs.getString("brandName"));
            medicamentEntity.setActiveIngredient(rs.getString("activeIngredient"));
            medicamentEntity.setDosage(rs.getDouble("dosage"));
            medicamentEntity.setPackingForm(PackingForm.valueOf(rs.getString("packingForm")));
            medicamentEntity.setInternationalNonproprietaryName(rs.getString("internationalNonproprietaryName"));
            medicamentEntity.setReleaseForm(ReleaseForm.valueOf(rs.getString("releaseForm")));
            medicamentEntity.setGuid(UUID.fromString(rs.getString("guid")));
            medicamentEntity.setId(rs.getLong("id"));
            return medicamentEntity;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<MedicamentEntity> getAll() {
        return jdbcTemplate.query(
                "select * from medicament" , mapper
        );
    }

    @Override
    public List<MedicamentEntity> getAll(MedicamentsSearchRequest request) {
        return null;
    }

    @Override
    public MedicamentEntity getEntityById(Long id) {
        return null;
    }

    @Override
    public MedicamentEntity getEntityByName(String name) {
        return null;
    }

    @Override
    public int countOf() {
        return 0;
    }

    @Override
    public void update(MedicamentEntity entity) throws EntityNotFoundException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public void create(MedicamentEntity entity) {

    }
}
