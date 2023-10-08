package com.example.cafespringBoot.dao.dessertArchiveDAO;

import com.example.cafespringBoot.model.DessertArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DessertArchiveDaoImpl implements DessertArchiveDao {
    private static final String FIND_ALL_DESSERTS_ARCHIVE = "SELECT * FROM desserts_archive";
    private static final String DELETE_ALL_DESSERTS_ARCHIVE = "DELETE FROM desserts_archive";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<DessertArchive> dessertArchiveRowMapper = new DessertArchiveRowMapper();

    @Override
    @Transactional(readOnly=true)
    public List<DessertArchive> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_DESSERTS_ARCHIVE, dessertArchiveRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_DESSERTS_ARCHIVE);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
