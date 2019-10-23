package com.livevox.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    JdbcTemplate jdbcTemplate;

    @Override
    public Customer find(long id) {
        String sql = "SELECT id, first_name, last_name FROM customers WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.queryForObject(sql, args, new CustomerRowMapper());
    }

    @Override
    public List<Customer> list() {
        String sql = "SELECT id, first_name, last_name FROM customers";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @Override
    public Customer create(String firstName, String lastName) {
        String sql = "INSERT INTO customers (first_name, last_name) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            return ps;
        }, keyHolder);
        return new Customer(keyHolder.getKey().longValue(), firstName, lastName);
    }

    @Override
    public void update(Customer cust) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ? WHERE id = ?";
        Object[] args = new Object[] {cust.getFirstName(), cust.getLastName(), cust.getId()};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        Object[] args = new Object[] {id};
        jdbcTemplate.update(sql, args);
    }

    public class CustomerRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );
        }
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
