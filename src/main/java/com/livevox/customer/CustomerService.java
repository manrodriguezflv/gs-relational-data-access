package com.livevox.customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> list();
    public Customer find(long id);
    public Customer create(String firstName, String lastName);
    public void update(Customer customer);
    public void delete(long id);
}
