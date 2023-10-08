package com.example.cafespringBoot.dao.staffDAO;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDataRowMapper implements RowMapper<StaffData> {
    public StaffData mapRow(ResultSet rs, int rowNum) throws SQLException {
        StaffData staffData = new StaffData();
        staffData.setId(rs.getLong("id"));
        staffData.setFullName(rs.getString("full_name"));
        staffData.setPhone(rs.getString("phone"));
        staffData.setEmail(rs.getString("email"));
        staffData.setPosition(rs.getString("position"));
        staffData.setCafeId(rs.getLong("cafe_id"));
        staffData.setDayOfWeek(rs.getString("day_of_week"));
        staffData.setStartTime(rs.getTime("start_time"));
        staffData.setEndTime(rs.getTime("end_time"));
        return staffData;
    }
}
