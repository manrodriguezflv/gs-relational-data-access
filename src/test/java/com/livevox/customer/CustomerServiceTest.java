package com.livevox.customer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    CustomerService service;
    CustomerDao dao;

    @Before
    public void setup() {
        dao = mock(CustomerDao.class);
        service = new CustomerServiceImpl(dao);
    }

    @Test
    public void testFindWithObject() {
        Customer expected = new Customer(1L, "First", "Last");
        when(dao.find(expected.getId())).thenReturn(expected);
        Customer actual = service.find(expected.getId());
        assertEquals(expected, actual);
    }
}
