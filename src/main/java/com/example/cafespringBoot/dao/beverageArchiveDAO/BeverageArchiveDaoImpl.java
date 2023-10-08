package com.example.cafespringBoot.dao.beverageArchiveDAO;

import com.example.cafespringBoot.dao.beverageDAO.BeverageRowMapper;
import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.BeverageArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeverageArchiveDaoImpl implements BeverageArchiveDao {
    private static final String FIND_ALL_BEVERAGES_ARCHIVE = "SELECT * FROM beverages_archive";
    private static final String DELETE_ALL_BEVERAGES_ARCHIVE = "DELETE FROM beverages_archive";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<BeverageArchive> beverageArchiveRowMapper = new BeverageArchiveRowMapper();

    @Override
    @Transactional(readOnly=true)
    public List<BeverageArchive> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_BEVERAGES_ARCHIVE, beverageArchiveRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_BEVERAGES_ARCHIVE);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
