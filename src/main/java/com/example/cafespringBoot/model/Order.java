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
public class Order {
    private Long id;
    private Long customerId;
    private Long staffId;
    private Date date;
    private double totalAmount;
    private Long cafeId;
}
