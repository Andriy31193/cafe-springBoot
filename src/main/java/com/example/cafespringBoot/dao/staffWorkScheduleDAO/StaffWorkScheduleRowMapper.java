package com.example.cafespringBoot.dao.staffWorkScheduleDAO;

import com.example.cafespringBoot.model.StaffWorkSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffWorkScheduleRowMapper implements RowMapper<StaffWorkSchedule> {
    public StaffWorkSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        StaffWorkSchedule workSchedule = new StaffWorkSchedule();
        workSchedule.setId(rs.getLong("id"));
        workSchedule.setStaffId(rs.getLong("staff_id"));
        workSchedule.setDayOfWeek(rs.getString("day_of_week"));
        workSchedule.setStartTime(rs.getTime("start_time"));
        workSchedule.setEndTime(rs.getTime("end_time"));
        return workSchedule;
    }
}
