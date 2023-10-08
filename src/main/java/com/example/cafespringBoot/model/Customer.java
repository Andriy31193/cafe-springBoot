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
public class Customer {
    private Long id;
    private String fullName;
    private Date birthDate;
    private String phone;
    private String email;
    private int discount;
    private Long cafeId;
}
