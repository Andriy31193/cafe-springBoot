package com.example.cafespringBoot.dao.beverageArchiveDAO;

import com.example.cafespringBoot.model.BeverageArchive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeverageArchiveRowMapper implements RowMapper<BeverageArchive> {
    public BeverageArchive mapRow(ResultSet rs, int rowNum) throws SQLException {
        BeverageArchive beverageArchive = new BeverageArchive();
        beverageArchive.setId(rs.getLong("id"));
        beverageArchive.setNameEnglish(rs.getString("name_english"));
        beverageArchive.setNameUkrainian(rs.getString("name_ukrainian"));
        beverageArchive.setPrice(rs.getDouble("price"));
        beverageArchive.setRating(rs.getDouble("rating"));
        beverageArchive.setCafeId(rs.getLong("cafe_id"));
        beverageArchive.setDeletedAt(rs.getTimestamp("deleted_at"));
        return beverageArchive;
    }
}
