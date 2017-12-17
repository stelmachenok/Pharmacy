package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

public class MedicamentCategoryJdbcDao implements InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
                "SELECT * FROM category",
                mapper
        );
    }

    @Override
    public List<MedicamentCategory> getAll(CategorySearchRequest request) {
        String SQL = "SELECT * FROM category ";
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
    public MedicamentCategory getEntityById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM category WHERE id = ?",
                    mapper,
                    id
            );
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<MedicamentCategory> getEntityByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM category WHERE categoryName = ?",
                mapper,
                name
        );
    }

    @Override
    public int countOf() {
        String SQL = "SELECT COUNT(*) FROM category";
        return jdbcTemplate.queryForObject(SQL, Integer.class);
    }

    @Override
    public int countOf(CategorySearchRequest request) {
        return 0;
    }

    @Override
    public void update(MedicamentCategory entity) throws EntityNotFoundException {
        String SQL = "UPDATE Category SET " +
                "categoryName = :categoryName, " +
                "description = :description " +
                "WHERE id = :id";
        Map namedParameters = new HashMap();
        namedParameters.put("categoryName", entity.getCategoryName());
        namedParameters.put("description", entity.getDescription());
        namedParameters.put("id", entity.getId());

        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        String SQL = "DELETE FROM category WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
        SQL = "UPDATE medicament SET medicamentCategory = NULL WHERE medicamentCategory = :id";
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public void create(MedicamentCategory entity) {
        String SQL = "INSERT INTO category (categoryName, description, guid)" +
                " VALUES (:categoryName, :description, :guid)";
        Map namedParameters = new HashMap();
        namedParameters.put("categoryName", entity.getCategoryName());
        namedParameters.put("description", entity.getDescription());
        namedParameters.put("guid", String.valueOf(entity.getGuid()));
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }
}
