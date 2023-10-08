package com.example.cafespringBoot.dao.beverageDAO;

import com.example.cafespringBoot.model.Beverage;
import com.example.cafespringBoot.model.Cafe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeverageRowMapper implements RowMapper<Beverage> {
    public Beverage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Beverage beverage = new Beverage();
        beverage.setId(rs.getLong("id"));
        beverage.setNameEnglish(rs.getString("name_english"));
        beverage.setNameUkrainian(rs.getString("name_ukrainian"));
        beverage.setPrice(rs.getDouble("price"));
        beverage.setRating(rs.getDouble("rating"));
        beverage.setCafeId(rs.getLong("cafe_id"));
        return beverage;
    }
}
