package com.example.cafespringBoot.dao.cafeDAO;

import com.example.cafespringBoot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CafeDaoImpl implements CafeDao {
    private static final String SAVE_CAFE = "INSERT INTO cafes(name, address) VALUES(?,?)";
    private static final String FIND_ALL_CAFES = "SELECT * FROM cafes";
    private static final String UPDATE_CAFE = "UPDATE cafes SET name=?, address=? WHERE cafes.id=?";
    private static final String DELETE_CAFE = "DELETE FROM cafes WHERE cafes.id = ?";
    private static final String DELETE_ALL_CAFES = "DELETE FROM cafes";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Cafe> cafeRowMapper = new CafeRowMapper();

    @Override
    public void save(Cafe cafe) {
        try {
            jdbcTemplate.update(SAVE_CAFE, cafe.getName(), cafe.getAddress());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Cafe> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_CAFES, cafeRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Cafe cafe) {
        try {
            jdbcTemplate.update(UPDATE_CAFE, cafe.getName(), cafe.getAddress(), cafe.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Cafe cafe) {
        try {
            jdbcTemplate.update(DELETE_CAFE, cafe.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_CAFES);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
