package com.example.cafespringBoot.dao.cafeDAO;

import com.example.cafespringBoot.model.Cafe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CafeRowMapper implements RowMapper<Cafe> {
    public Cafe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cafe cafe = new Cafe();
        cafe.setId(rs.getLong("id"));
        cafe.setName(rs.getString("name"));
        cafe.setAddress(rs.getString("address"));
        return cafe;
    }
}
