package com.example.cafespringBoot.dao.beverageDAO;

import com.example.cafespringBoot.model.Beverage;

import java.util.List;

public interface BeverageDao {
    void save(Beverage beverage);
    Beverage getBeverageById(Beverage beverage);
    List<Beverage> getAll();
    void update(Beverage beverage);
    void updateBeveragePrice(Beverage beverage);
    void delete(Beverage beverage);
    void deleteAll();
}
