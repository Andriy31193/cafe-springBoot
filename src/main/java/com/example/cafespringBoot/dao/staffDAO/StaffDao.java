package com.example.cafespringBoot.dao.staffDAO;

import com.example.cafespringBoot.model.Staff;

import java.util.List;

public interface StaffDao {
    void save(Staff staff);
    void addBarista(Staff staff);
    void addConfectioner(Staff staff);
    Staff getStaffById(Staff staff);
    List<StaffData> getAllBaristas();
    List<StaffData> getAllWaiter();
    List<Staff> getAll();
    void update(Staff staff);
    void updateConfectionerEmailInfo(Staff staff);
    void updateBaristaPhoneInfo(Staff staff);
    void transferStaffToCafe(Staff staff);
    void delete(Staff staff);
    void deleteWaiterById(Staff staff);
    void deleteBaristaById(Staff staff);
    void deleteAll();
}
