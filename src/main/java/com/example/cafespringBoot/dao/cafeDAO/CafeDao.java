package com.example.cafespringBoot.dao.cafeDAO;

import com.example.cafespringBoot.model.Cafe;

import java.util.List;

public interface CafeDao {
    void save(Cafe cafe);
    List<Cafe> getAll();
    void update(Cafe cafe);
    void delete(Cafe cafe);
    void deleteAll();
}
