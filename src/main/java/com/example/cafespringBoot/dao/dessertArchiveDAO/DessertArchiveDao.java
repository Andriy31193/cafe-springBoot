package com.example.cafespringBoot.dao.dessertArchiveDAO;

import com.example.cafespringBoot.model.DessertArchive;

import java.util.List;

public interface DessertArchiveDao {
    List<DessertArchive> getAll();
    void deleteAll();
}
