package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.User;
import by.samsolution.pharmacy.entity.UserRole;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao implements InterfaceDAO<User, Long, String, UserSearchRequest>{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    class Mapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRole(UserRole.valueOf(rs.getString("role")));
            user.setPharmacyId(rs.getLong("pharmacyId"));
            return user;
        }
    }

    private final Mapper mapper = new Mapper();

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAll(UserSearchRequest request) {
        return null;
    }

    @Override
    public User getEntityById(Long id) {
        return null;
    }

    @Override
    public List<User> getEntityByName(String login) {
        return jdbcTemplate.query(
                "SELECT * FROM userTable WHERE login = ?",
                mapper,
                login
        );
    }

    @Override
    public int countOf() {
        return 0;
    }

    @Override
    public void update(User entity) throws EntityNotFoundException, JdbcManipulationException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {

    }

    @Override
    public void create(User entity) throws JdbcManipulationException {

    }
}
