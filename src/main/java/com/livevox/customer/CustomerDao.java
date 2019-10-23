package com.livevox.customer;

import java.util.List;

public interface CustomerDao {

    public Customer find(long id);
    public List<Customer> list();
    public Customer create(String firstName, String lastName);
    public void update(Customer cust);
    public void delete(long id);
}
