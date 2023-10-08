package com.example.cafespringBoot.dao.customerDAO;


import com.example.cafespringBoot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO public.customers (full_name, birth_date, phone, email, discount, cafe_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_MIN_DISCOUNT_QUERY = "SELECT MIN(discount) AS min_discount FROM public.customers";
    private static final String SELECT_MAX_DISCOUNT_QUERY = "SELECT MAX(discount) AS max_discount FROM public.customers";
    private static final String SELECT_CUSTOMERS_WITH_MIN_DISCOUNT_QUERY = "SELECT * FROM public.customers WHERE discount = (SELECT MIN(discount) FROM public.customers)";
    private static final String SELECT_CUSTOMERS_WITH_MAX_DISCOUNT_QUERY = "SELECT * FROM public.customers WHERE discount = (SELECT MAX(discount) FROM public.customers)";
    private static final String SELECT_AVERAGE_DISCOUNT_QUERY = "SELECT AVG(discount) AS average_discount FROM public.customers";
    private static final String SELECT_YOUNGEST_CUSTOMER_QUERY = "SELECT * FROM public.customers WHERE birth_date = (SELECT MAX(birth_date) FROM public.customers)";
    private static final String SELECT_OLDEST_CUSTOMER_QUERY = "SELECT * FROM public.customers WHERE birth_date = (SELECT MIN(birth_date) FROM public.customers)";
    private static final String SELECT_CUSTOMERS_BY_BIRTHDAY_QUERY = "SELECT * FROM public.customers WHERE EXTRACT(MONTH FROM birth_date) = ? AND EXTRACT(DAY FROM birth_date) = ?";
    private static final String SELECT_CUSTOMERS_WITH_NULL_EMAIL_QUERY = "SELECT * FROM public.customers WHERE email = ''";
    private static final String UPDATE_CUSTOMER_DISCOUNT_QUERY = "UPDATE public.customers SET discount = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM public.customers WHERE id=?";
    private static final String DELETE_ALL_CUSTOMER_QUERY = "DELETE FROM public.customers";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Customer> customerRowMapper = new CustomerRowMapper();

    @Override
    public void save(Customer customer) {
        try {
            jdbcTemplate.update(INSERT_CUSTOMER_QUERY, customer.getFullName(), customer.getBirthDate(), customer.getPhone(), customer.getEmail(), customer.getDiscount(), customer.getCafeId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getMinDiscount() {
        try {
            return jdbcTemplate.queryForObject(SELECT_MIN_DISCOUNT_QUERY, Integer.class);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public int getMaxDiscount() {
        try {
            return jdbcTemplate.queryForObject(SELECT_MAX_DISCOUNT_QUERY, Integer.class);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public List<Customer> getCustomersWithMinDiscount() {
        try {
            return jdbcTemplate.query(SELECT_CUSTOMERS_WITH_MIN_DISCOUNT_QUERY, customerRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Customer> getCustomersWithMaxDiscount() {
        try {
            return jdbcTemplate.query(SELECT_CUSTOMERS_WITH_MAX_DISCOUNT_QUERY, customerRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public int getAverageDiscount() {
        try {
            return jdbcTemplate.queryForObject(SELECT_AVERAGE_DISCOUNT_QUERY, Integer.class);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public Customer getYoungestCustomer() {
        try {
            return jdbcTemplate.queryForObject(SELECT_YOUNGEST_CUSTOMER_QUERY, new BeanPropertyRowMapper<>(Customer.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getOldestCustomer() {
        try {
            return jdbcTemplate.queryForObject(SELECT_OLDEST_CUSTOMER_QUERY, new BeanPropertyRowMapper<>(Customer.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> getCustomersWithBirthdayToday() {
        try {
            return jdbcTemplate.query(SELECT_CUSTOMERS_BY_BIRTHDAY_QUERY, customerRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Customer> getCustomersWithNullEmail() {
        try {
            return jdbcTemplate.query(SELECT_CUSTOMERS_WITH_NULL_EMAIL_QUERY, customerRowMapper);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateCustomerDiscount(Customer customer) {
        try {
            jdbcTemplate.update(UPDATE_CUSTOMER_DISCOUNT_QUERY, customer.getDiscount(), customer.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            jdbcTemplate.update(DELETE_CUSTOMER_QUERY, customer.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_CUSTOMER_QUERY);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }
}
