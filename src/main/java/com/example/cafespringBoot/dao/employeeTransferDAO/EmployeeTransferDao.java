package com.example.cafespringBoot.dao.employeeTransferDAO;

import com.example.cafespringBoot.model.EmployeeTransfer;

import java.util.List;

public interface EmployeeTransferDao {
    List<EmployeeTransfer> getAll();
    void deleteAll();
}
