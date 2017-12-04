package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MedicamentCategoryJdbcDao implements InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class Mapper implements RowMapper<MedicamentCategory> {

        @Override
        public MedicamentCategory mapRow(ResultSet rs, int i) throws SQLException {
            MedicamentCategory category = new MedicamentCategory();
            category.setId(Long.valueOf(rs.getString("id")));
            category.setCategoryName(rs.getString("categoryName"));
            category.setDescription(rs.getString("description"));
            category.setGuid(UUID.fromString(rs.getString("guid")));
            return category;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<MedicamentCategory> getAll() {
        return jdbcTemplate.query(
                "select * from category",
                mapper
        );
    }

    @Override
    public List<MedicamentCategory> getAll(CategorySearchRequest request) {
        return null;
    }

    @Override
    public MedicamentCategory getEntityById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from category WHERE id = ?",
                mapper,
                id
        );
    }

    @Override
    public MedicamentCategory getEntityByName(String name) {
        return null;
    }

    @Override
    public int countOf() {
        return 0;
    }

    @Override
    public void update(MedicamentCategory entity) throws EntityNotFoundException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public void create(MedicamentCategory entity) {

    }
}
