package com.livevox.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private static final String JSON_MIME = "application/json";
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }
	
    @GetMapping(value = "/customer", produces =  JSON_MIME)
    public List<Customer> listCustomers(HttpServletRequest request, HttpServletResponse response) {
		return service.list();
    }
    
    @GetMapping(value = "/customer/{id}", produces =  "application/json")
    public Customer getCustomer(@PathVariable long id) {
		return service.find(id);
    }
    
    @PutMapping(value = "/customer/{id}", consumes =  "application/json", produces =  "application/json")
    public void updateCustomer(@PathVariable long id, @RequestBody CustomerRequest body,  HttpServletRequest request, HttpServletResponse response) {
        Customer customer = new Customer(id, body.getFirstName(), body.getLastName());
        service.update(customer);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping(value = "/customer", consumes =  "application/json", produces =  "application/json")
    public void createCustomer(@RequestBody CustomerRequest body, HttpServletRequest req, HttpServletResponse res) {
        Customer cust = service.create(body.getFirstName(), body.getLastName());
        res.setStatus(HttpServletResponse.SC_CREATED);
        res.addHeader("Location", String.format("%s/%d",req.getRequestURL(), cust.getId()));
    }
    
    @DeleteMapping(value = "/customer/{id}")
    public void deleteCustomer(@PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
    	service.delete(id);
    	res.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

}
