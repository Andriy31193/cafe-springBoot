package com.example.cafespringBoot.dao.customerDAO;

import com.example.cafespringBoot.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setBirthDate(rs.getDate("birth_date"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        customer.setDiscount(rs.getInt("discount"));
        customer.setCafeId(rs.getLong("cafe_id"));
        return customer;
    }
}
