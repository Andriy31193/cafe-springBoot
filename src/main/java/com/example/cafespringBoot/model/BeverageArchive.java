package com.example.cafespringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeverageArchive {
    private Long id;
    private String nameEnglish;
    private String nameUkrainian;
    private double price;
    private double rating;
    private Long cafeId;
    private Timestamp deletedAt;
}
