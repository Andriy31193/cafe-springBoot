package com.example.cafespringBoot.dao.dessertDAO;

import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Cafe;
import com.example.cafespringBoot.model.Dessert;

import java.util.List;

public interface DessertDao {
    void save(Dessert dessert);
    Dessert getDessertById(Dessert dessert);
    List<Dessert> getAll();
    void update(Dessert dessert);
    void delete(Dessert dessert);
    void deleteAll();
}
