package com.example.cafespringBoot.dao.staffWorkScheduleDAO;

import com.example.cafespringBoot.model.Staff;
import com.example.cafespringBoot.model.StaffWorkSchedule;

import java.util.List;

public interface StaffWorkScheduleDao {
    void save(StaffWorkSchedule workSchedule);
    Staff getStaffById(Staff staff);
    List<StaffWorkSchedule> getBaristaScheduleForWeek(Staff staff);
    List<StaffWorkSchedule> getAllBaristasScheduleForWeek();
    List<StaffWorkSchedule> getAllStaffScheduleForWeek();
    void deleteAll();
}
