package com.example.cafespringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTransfer {
    private Long id;
    private Long employeeId;
    private Long oldCafeId;
    private Long newCafeId;
    private Date transferDate;
}
