package com.example.cafespringBoot.dao.dessertDAO;

import com.example.cafespringBoot.model.Dessert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DessertRowMapper implements RowMapper<Dessert> {
    public Dessert mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dessert dessert = new Dessert();
        dessert.setId(rs.getLong("id"));
        dessert.setNameEnglish(rs.getString("name_english"));
        dessert.setNameUkrainian(rs.getString("name_ukrainian"));
        dessert.setPrice(rs.getDouble("price"));
        dessert.setRating(rs.getDouble("rating"));
        dessert.setCafeId(rs.getLong("cafe_id"));
        return dessert;
    }
}
