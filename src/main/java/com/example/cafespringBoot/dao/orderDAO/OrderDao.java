package com.example.cafespringBoot.dao.orderDAO;

import com.example.cafespringBoot.model.Customer;
import com.example.cafespringBoot.model.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
    void save(Order order);
    List<Order> getOrdersByDate(Date date);
    List<Order> getOrdersInDateRange(Date startDate, Date endDate);
    double getAverageOrderAmountByDate(Date date);
    double getMaxOrderAmountByDate(Date date);
    Customer getClientWithMaxOrderAmountByDate(Date date);
    void delete(Order order);
    void deleteAll();
}
