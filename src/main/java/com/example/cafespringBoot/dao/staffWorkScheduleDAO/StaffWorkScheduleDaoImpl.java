package com.example.cafespringBoot.dao.staffWorkScheduleDAO;

import com.example.cafespringBoot.model.Customer;
import com.example.cafespringBoot.model.Staff;
import com.example.cafespringBoot.model.StaffWorkSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StaffWorkScheduleDaoImpl implements StaffWorkScheduleDao {
    private static final String INSERT_SCHEDULE_QUERY = "INSERT INTO public.staff_work_schedule (staff_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STAFF_BY_ID_SQL = "SELECT * FROM public.staff WHERE id=?";
    private static final String SELECT_BARISTA_SCHEDULE_FOR_WEEK_QUERY = "SELECT * FROM public.staff_work_schedule WHERE staff_id = ? " +
            "AND day_of_week IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')";
    private static final String SELECT_ALL_BARISTAS_SCHEDULE_FOR_WEEK_QUERY = "SELECT * FROM public.staff_work_schedule\n" +
            "JOIN public.staff ON staff_work_schedule.staff_id = staff.id\n" +
            "WHERE staff.position = 'barista' AND staff_work_schedule.day_of_week IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');";
    private static final String SELECT_ALL_STAFF_SCHEDULE_FOR_WEEK_QUERY = "SELECT * FROM public.staff_work_schedule WHERE staff_work_schedule.day_of_week " +
            "IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')";
    private static final String DELETE_ALL_STAFF_WORK_SCHEDULE_QUERY = "DELETE FROM public.staff_work_schedule";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<StaffWorkSchedule> scheduleRowMapper = new StaffWorkScheduleRowMapper();

    @Override
    public void save(StaffWorkSchedule workSchedule) {
        try {
            jdbcTemplate.update(INSERT_SCHEDULE_QUERY, workSchedule.getStaffId(), workSchedule.getDayOfWeek(), workSchedule.getStartTime(), workSchedule.getEndTime());
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
    public List<StaffWorkSchedule> getBaristaScheduleForWeek(Staff staff) {
        Staff staff1 = getStaffById(staff);
        List<StaffWorkSchedule> baristaScheduleForWeek = new ArrayList<>();
        if (staff1.getPosition().equals("barista")) {
            try {
                return jdbcTemplate.query(SELECT_BARISTA_SCHEDULE_FOR_WEEK_QUERY, scheduleRowMapper);
            } catch (DataAccessException e) {
                System.err.println(e.getMessage());
                return new ArrayList<>();
            }
        } else {
            System.err.println("Invalid position: " + staff1.getPosition());
        }

        return baristaScheduleForWeek;
    }

    @Override
    public List<StaffWorkSchedule> getAllBaristasScheduleForWeek() {
        try {
            return jdbcTemplate.query(SELECT_ALL_BARISTAS_SCHEDULE_FOR_WEEK_QUERY, scheduleRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<StaffWorkSchedule> getAllStaffScheduleForWeek() {
        try {
            return jdbcTemplate.query(SELECT_ALL_STAFF_SCHEDULE_FOR_WEEK_QUERY, scheduleRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_STAFF_WORK_SCHEDULE_QUERY);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

}
