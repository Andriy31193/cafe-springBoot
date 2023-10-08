package com.example.cafespringBoot.dao.staffDAO;

import com.example.cafespringBoot.dao.beverageDAO.BeverageRowMapper;
import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffDaoImpl implements StaffDao {
    private static final String INSERT_STAFF_QUERY = "INSERT INTO public.staff (full_name, phone, email, position, cafe_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_STAFF_BY_ID_SQL = "SELECT * FROM public.staff WHERE id=?";
    private static final String SELECT_ALL_STAFF_QUERY = "SELECT * FROM public.staff";
    private static final String SELECT_ALL_BARISTA_QUERY = "SELECT s.id, s.full_name, s.phone, s.email, s.position, s.cafe_id, sws.day_of_week, sws.start_time, sws.end_time " +
            "FROM public.staff s " +
            "LEFT JOIN public.staff_work_schedule sws ON s.id = sws.staff_id " +
            "WHERE s.position = 'barista'";

    private static final String SELECT_ALL_WAITER_QUERY = "SELECT s.id, s.full_name, s.phone, s.email, s.position, s.cafe_id, sws.day_of_week, sws.start_time, sws.end_time " +
            "FROM public.staff s " +
            "LEFT JOIN public.staff_work_schedule sws ON s.id = sws.staff_id " +
            "WHERE s.position = 'waiter'";
    private static final String UPDATE_STAFF_QUERY = "UPDATE public.staff SET full_name=?, phone=?, email=?, position=?, cafe_id=? WHERE id=?";
    private static final String UPDATE_CONFECTIONER_EMAIL_QUERY = "UPDATE public.staff SET email = ? WHERE id = ?";
    private static final String UPDATE_BARISTA_PHONE_QUERY = "UPDATE public.staff SET phone = ? WHERE id = ?";
    private static final String UPDATE_TRANSFER_STAFF_QUERY = "UPDATE staff SET cafe_id = ? WHERE id = ?";
    private static final String DELETE_STAFF_QUERY = "DELETE FROM public.staff WHERE id=?";
    private static final String DELETE_ALL_STAFF_QUERY = "DELETE FROM public.staff";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Staff> staffRowMapper = new StaffRowMapper();
    private final RowMapper<StaffData> staffDataRowMapper = new StaffDataRowMapper();

    @Override
    public void save(Staff staff) {
        if (isValidPosition(staff.getPosition())) {
            try {
                jdbcTemplate.update(INSERT_STAFF_QUERY, staff.getFullName(), staff.getPhone(), staff.getEmail(), staff.getPosition(), staff.getCafeId());
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Invalid position: " + staff.getPosition());
        }
    }

    private boolean isValidPosition(String position) {
        return position.equals("barista") || position.equals("waiter") || position.equals("confectioner");
    }

    @Override
    public void addBarista(Staff staff) {
        try {
            jdbcTemplate.update(INSERT_STAFF_QUERY, staff.getFullName(), staff.getPhone(), staff.getEmail(), "barista", staff.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addConfectioner(Staff staff) {
        try {
            jdbcTemplate.update(INSERT_STAFF_QUERY, staff.getFullName(), staff.getPhone(), staff.getEmail(), "confectioner", staff.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Staff getStaffById(Staff staff) {
        try {
            return jdbcTemplate.queryForObject(SELECT_STAFF_BY_ID_SQL, new Object[]{staff.getId()}, new BeanPropertyRowMapper<>(Staff.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<StaffData> getAllBaristas() {
        try {
            return jdbcTemplate.query(SELECT_ALL_BARISTA_QUERY, staffDataRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<StaffData> getAllWaiter() {
        try {
            return jdbcTemplate.query(SELECT_ALL_WAITER_QUERY, staffDataRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Staff> getAll() {
        try {
            return jdbcTemplate.query(SELECT_ALL_STAFF_QUERY, staffRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Staff staff) {
        try {
            jdbcTemplate.update(UPDATE_STAFF_QUERY, staff.getFullName(), staff.getPhone(), staff.getEmail(), staff.getPosition(), staff.getCafeId(), staff.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateConfectionerEmailInfo(Staff staff) {
        Staff staff1 = getStaffById(staff);
        if (staff1.getPosition().equals("confectioner")) {
            try {
                jdbcTemplate.update(UPDATE_CONFECTIONER_EMAIL_QUERY, staff.getEmail(), staff.getId());
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Invalid position: " + staff1.getPosition());
        }
    }

    @Override
    public void updateBaristaPhoneInfo(Staff staff) {
        Staff staff1 = getStaffById(staff);
        if (staff1.getPosition().equals("barista")) {
            try {
                jdbcTemplate.update(UPDATE_BARISTA_PHONE_QUERY, staff.getPhone(), staff.getId());
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Invalid position: " + staff1.getPosition());
        }
    }

    @Override
    public void transferStaffToCafe(Staff staff) {
        try {
            jdbcTemplate.update(UPDATE_TRANSFER_STAFF_QUERY, staff.getCafeId(), staff.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Staff staff) {
        try {
            jdbcTemplate.update(DELETE_STAFF_QUERY, staff.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteWaiterById(Staff staff) {
        Staff staff1 = getStaffById(staff);
        if (staff1.getPosition().equals("waiter")) {
            try {
                jdbcTemplate.update(DELETE_STAFF_QUERY, staff.getId());
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Invalid position: " + staff1.getPosition());
        }
    }

    @Override
    public void deleteBaristaById(Staff staff) {
        Staff staff1 = getStaffById(staff);
        if (staff1.getPosition().equals("barista")) {
            try {
                jdbcTemplate.update(DELETE_STAFF_QUERY, staff.getId());
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Invalid position: " + staff1.getPosition());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_STAFF_QUERY);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
