package com.example.cafespringBoot.dao.dessertDAO;

import com.example.cafespringBoot.dao.beverageDAO.BeverageDao;
import com.example.cafespringBoot.dao.beverageDAO.BeverageRowMapper;
import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Dessert;
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
public class DessertDaoImpl implements DessertDao {
    private static final String SAVE_DESSERT = "INSERT INTO desserts (name_english, name_ukrainian, price, rating, cafe_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_DESSERT_BY_ID = "SELECT * FROM desserts WHERE id=?";
    private static final String FIND_ALL_DESSERTS = "SELECT * FROM desserts";
    private static final String UPDATE_DESSERT = "UPDATE desserts SET name=?, address=?, price=?, rating=?, cafe_id=? WHERE desserts.id=?";
    private static final String DELETE_DESSERT = "DELETE FROM desserts WHERE desserts.id = ?";
    private static final String DELETE_ALL_DESSERTS = "DELETE FROM desserts";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Dessert> dessertRowMapper = new DessertRowMapper();

    @Override
    public void save(Dessert d) {
        try {
            jdbcTemplate.update(SAVE_DESSERT, d.getNameEnglish(), d.getNameUkrainian(), d.getPrice(), d.getRating(), d.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Dessert getDessertById(Dessert d) {
        try {
            return jdbcTemplate.queryForObject(SELECT_DESSERT_BY_ID, new Object[]{d.getId()}, new BeanPropertyRowMapper<>(Dessert.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Dessert> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_DESSERTS, dessertRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Dessert d) {
        try {
            jdbcTemplate.update(UPDATE_DESSERT, d.getNameEnglish(), d.getNameUkrainian(), d.getPrice(), d.getRating(), d.getCafeId(), d.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Dessert d) {
        try {
            jdbcTemplate.update(DELETE_DESSERT, d.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_DESSERTS);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
