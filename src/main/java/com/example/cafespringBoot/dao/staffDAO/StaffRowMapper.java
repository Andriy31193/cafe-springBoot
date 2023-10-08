package com.example.cafespringBoot.dao.staffDAO;

import com.example.cafespringBoot.model.Dessert;
import com.example.cafespringBoot.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getLong("id"));
        staff.setFullName(rs.getString("full_name"));
        staff.setPhone(rs.getString("phone"));
        staff.setEmail(rs.getString("email"));
        staff.setPosition(rs.getString("position"));
        staff.setCafeId(rs.getLong("cafe_id"));
        return staff;
    }
}
