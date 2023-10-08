package com.example.cafespringBoot.dao.dessertArchiveDAO;

import com.example.cafespringBoot.model.DessertArchive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DessertArchiveRowMapper implements RowMapper<DessertArchive> {
    public DessertArchive mapRow(ResultSet rs, int rowNum) throws SQLException {
        DessertArchive dessertArchive = new DessertArchive();
        dessertArchive.setId(rs.getLong("id"));
        dessertArchive.setNameEnglish(rs.getString("name_english"));
        dessertArchive.setNameUkrainian(rs.getString("name_ukrainian"));
        dessertArchive.setPrice(rs.getDouble("price"));
        dessertArchive.setRating(rs.getDouble("rating"));
        dessertArchive.setCafeId(rs.getLong("cafe_id"));
        dessertArchive.setDeletedAt(rs.getTimestamp("deleted_at"));
        return dessertArchive;
    }
}
