package com.livevox.customer;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class CustomerServiceIT extends BaseIT {

    @Autowired
    private CustomerService service;

    @Test
    public void testExists() {
        assertNotNull(service);
    }
    @Test
    public void testList() {
        service.list().forEach(cust -> System.out.println(cust));
    }

    @Test
    public void testFind() {
        System.out.println(service.find(1L));
    }

    @Test
    public void testCreate() {
        System.out.println(service.create("Kurt", "Sorge"));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        Customer c = service.create("Kurt", "Sorge");
        service.delete(c.getId());
        Customer c2 = service.find(c.getId());
    }

    @Test
    public void testUpdate() {
        Customer c = service.create("Kurt", "Sorge");
        service.update(new Customer(c.getId(), "Guy", "Steele"));
        Customer c2 = service.find(c.getId());
        System.out.println(c2);
    }

}
