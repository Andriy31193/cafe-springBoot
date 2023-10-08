package com.example.cafespringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffWorkSchedule {
    private Long id;
    private Long staffId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
}
