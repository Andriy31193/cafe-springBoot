package com.example.cafespringBoot.dao.staffDAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffData {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private Long cafeId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;

}