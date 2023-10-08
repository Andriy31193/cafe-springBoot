package com.example.cafespringBoot.dao.customerDAO;

import com.example.cafespringBoot.model.Customer;

import java.util.List;

public interface CustomerDao {
    void save(Customer customer);
    int getMinDiscount();
    int getMaxDiscount();
    List<Customer> getCustomersWithMinDiscount();
    List<Customer> getCustomersWithMaxDiscount();
    int getAverageDiscount();
    Customer getYoungestCustomer();
    Customer getOldestCustomer();
    List<Customer> getCustomersWithBirthdayToday();
    List<Customer> getCustomersWithNullEmail();
    void updateCustomerDiscount(Customer customer);
    void delete(Customer customer);
    void deleteAll();
}
