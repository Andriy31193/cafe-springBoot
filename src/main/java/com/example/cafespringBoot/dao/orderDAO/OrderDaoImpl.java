package com.example.cafespringBoot.dao.orderDAO;

import com.example.cafespringBoot.model.Customer;
import com.example.cafespringBoot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final String INSERT_ORDER_QUERY = "INSERT INTO public.orders (customer_id, staff_id, date, total_amount, cafe_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ORDERS_BY_DATE_QUERY = "SELECT * FROM public.orders WHERE date = ?";
    private static final String SELECT_ORDERS_IN_DATE_RANGE_QUERY = "SELECT * FROM public.orders WHERE date BETWEEN ? AND ?";
    private static final String AVERAGE_ORDER_AMOUNT_BY_DATE_QUERY = "SELECT AVG(total_amount) AS average_order_amount FROM public.orders WHERE date = ?";
    private static final String MAX_ORDER_AMOUNT_BY_DATE_QUERY = "SELECT MAX(total_amount) AS max_order_amount FROM public.orders WHERE date = ?";
    private static final String CLIENT_WITH_MAX_ORDER_AMOUNT_BY_DATE_QUERY = "SELECT * FROM public.orders JOIN public.customers ON orders.customer_id = customers.id WHERE orders.date = ? AND orders.total_amount = (SELECT MAX(total_amount) FROM public.orders WHERE date = ?)";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM public.orders WHERE id=?";
    private static final String DELETE_ALL_ORDER_QUERY = "DELETE FROM public.orders";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Order> orderRowMapper = new OrderRowMapper();

    @Override
    public void save(Order order) {
        try {
            jdbcTemplate.update(INSERT_ORDER_QUERY, order.getCustomerId(), order.getStaffId(), order.getDate(), order.getTotalAmount(), order.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        try {
            return jdbcTemplate.query(SELECT_ORDERS_BY_DATE_QUERY, orderRowMapper, date);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Order> getOrdersInDateRange(Date startDate, Date endDate) {
        try {
            return jdbcTemplate.query(SELECT_ORDERS_IN_DATE_RANGE_QUERY, orderRowMapper, startDate, endDate);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public double getAverageOrderAmountByDate(Date date) {
        try {
            return jdbcTemplate.queryForObject(AVERAGE_ORDER_AMOUNT_BY_DATE_QUERY, new Object[]{date}, Double.class);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public double getMaxOrderAmountByDate(Date date) {
        try {
            return jdbcTemplate.queryForObject(MAX_ORDER_AMOUNT_BY_DATE_QUERY, new Object[]{date}, Double.class);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public Customer getClientWithMaxOrderAmountByDate(Date date) {
        try {
            return jdbcTemplate.queryForObject(CLIENT_WITH_MAX_ORDER_AMOUNT_BY_DATE_QUERY, new Object[]{date}, new BeanPropertyRowMapper<>(Customer.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Order order) {
        try {
            jdbcTemplate.update(DELETE_ORDER_QUERY, order.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_ORDER_QUERY);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
