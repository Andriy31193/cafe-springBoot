package com.example.cafespringBoot.dao.orderDAO;

import com.example.cafespringBoot.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setCustomerId(rs.getLong("customer_id"));
        order.setStaffId(rs.getLong("staff_id"));
        order.setDate(rs.getDate("date"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setCafeId(rs.getLong("cafe_id"));
        return order;
    }
}
