package com.example.cafespringBoot.dao.beverageDAO;

import com.example.cafespringBoot.dao.cafeDAO.CafeDao;
import com.example.cafespringBoot.dao.cafeDAO.CafeRowMapper;
import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeverageDaoImpl implements BeverageDao {
    private static final String SAVE_BEVERAGE = "INSERT INTO beverages (name_english, name_ukrainian, price, rating, cafe_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BEVERAGE_BY_ID = "SELECT * FROM beverages WHERE id=?";
    private static final String FIND_ALL_BEVERAGES = "SELECT * FROM beverages";
    private static final String UPDATE_BEVERAGE = "UPDATE beverages SET name=?, address=?, price=?, rating=?, cafe_id=? WHERE beverages.id=?";
    private static final String UPDATE_BEVERAGE_PRICE_QUERY = "UPDATE beverages SET price = ? WHERE id = ?";
    private static final String DELETE_BEVERAGE = "DELETE FROM beverages WHERE beverages.id = ?";
    private static final String DELETE_ALL_BEVERAGES = "DELETE FROM beverages";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Beverage> beverageRowMapper = new BeverageRowMapper();

    @Override
    public void save(Beverage b) {
        try {
            jdbcTemplate.update(SAVE_BEVERAGE, b.getNameEnglish(), b.getNameUkrainian(), b.getPrice(), b.getRating(), b.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Beverage getBeverageById(Beverage b) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BEVERAGE_BY_ID, new Object[]{b.getId()}, new BeanPropertyRowMapper<>(Beverage.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Beverage> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_BEVERAGES, beverageRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Beverage b) {
        try {
            jdbcTemplate.update(UPDATE_BEVERAGE, b.getNameEnglish(), b.getNameUkrainian(), b.getPrice(), b.getRating(), b.getCafeId(), b.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateBeveragePrice(Beverage b) {
        try {
            jdbcTemplate.update(UPDATE_BEVERAGE_PRICE_QUERY, b.getPrice(), b.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Beverage b) {
        try {
            jdbcTemplate.update(DELETE_BEVERAGE, b.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_BEVERAGES);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
