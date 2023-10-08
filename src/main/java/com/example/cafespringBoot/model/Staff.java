package com.example.cafespringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private Long cafeId;
}
