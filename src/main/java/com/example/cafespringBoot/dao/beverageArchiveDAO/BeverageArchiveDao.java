package com.example.cafespringBoot.dao.beverageArchiveDAO;

import com.example.cafespringBoot.model.BeverageArchive;

import java.util.List;

public interface BeverageArchiveDao {
    List<BeverageArchive> getAll();
    void deleteAll();
}
