package com.livevox.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao dao;

    @Autowired
    public CustomerServiceImpl(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Customer> list() {
        return dao.list();
    }

    @Override
    public Customer find(long id) {
        return dao.find(id);
    }

    @Override
    public Customer create(String firstName, String lastName) {
        return dao.create(firstName, lastName);
    }

    @Override
    public void update(Customer customer) {
        dao.update(customer);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

}
