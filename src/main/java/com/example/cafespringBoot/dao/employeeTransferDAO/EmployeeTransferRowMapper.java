package com.example.cafespringBoot.dao.employeeTransferDAO;

import com.example.cafespringBoot.model.EmployeeTransfer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeTransferRowMapper implements RowMapper<EmployeeTransfer> {
    public EmployeeTransfer mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeTransfer employeeTransfer = new EmployeeTransfer();
        employeeTransfer.setId(rs.getLong("id"));
        employeeTransfer.setEmployeeId(rs.getLong("employee_id"));
        employeeTransfer.setOldCafeId(rs.getLong("old_cafe_id"));
        employeeTransfer.setNewCafeId(rs.getLong("new_cafe_id"));
        employeeTransfer.setTransferDate(rs.getDate("transfer_date"));
        return employeeTransfer;
    }
}
