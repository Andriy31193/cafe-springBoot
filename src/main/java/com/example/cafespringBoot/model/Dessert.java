package com.example.cafespringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dessert {
    private Long id;
    private String nameEnglish;
    private String nameUkrainian;
    private double price;
    private double rating;
    private Long cafeId;
}
