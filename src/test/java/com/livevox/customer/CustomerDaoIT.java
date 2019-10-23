package com.livevox.customer;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDaoIT extends BaseIT {

    @Autowired
    private CustomerDao dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDaoExists() {
        assertNotNull(dao);
    }

    @Test
    public void TemplateExists() {
        assertNotNull(jdbcTemplate);
    }

    @Test
    public void testList() {
        dao.list().forEach(cust -> System.out.println(cust));
    }

    @Test
    public void testFind() {
        System.out.println(dao.find(1L));
    }

    @Test
    public void testCreate() {
        System.out.println(dao.create("Kurt", "Sorge"));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        Customer c = dao.create("Kurt", "Sorge");
        dao.delete(c.getId());
        Customer c2 = dao.find(c.getId());
    }

    @Test
    public void testUpdate() {
        Customer c = dao.create("Kurt", "Sorge");
        dao.update(new Customer(c.getId(), "Guy", "Steele"));
        Customer c2 = dao.find(c.getId());
        System.out.println(c2);
    }
}
