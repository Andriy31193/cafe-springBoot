package com.example.cafespringBoot.dao.employeeTransferDAO;

import com.example.cafespringBoot.model.EmployeeTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeTransferDaoImpl implements EmployeeTransferDao {
    private static final String FIND_ALL_EMPLOYEE_TRANSFERS = "SELECT * FROM employee_transfers";
    private static final String DELETE_ALL_EMPLOYEE_TRANSFERS = "DELETE FROM employee_transfers";


    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<EmployeeTransfer> employeeTransferRowMapper = new EmployeeTransferRowMapper();

    @Override
    @Transactional(readOnly=true)
    public List<EmployeeTransfer> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_EMPLOYEE_TRANSFERS, employeeTransferRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_EMPLOYEE_TRANSFERS);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
