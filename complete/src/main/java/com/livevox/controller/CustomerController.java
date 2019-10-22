package com.livevox.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.livevox.Customer;

@RestController
public class CustomerController {
	
	@Autowired
	JdbcTemplate template;

    @GetMapping("/customer/list")
    public List<Customer> listCustomers(HttpServletRequest request, HttpServletResponse response) {
    	List<Customer> list =  new ArrayList<>();
		template.query(
                "SELECT id, first_name, last_name FROM customers",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> {list .add(customer);System.out.println(customer.toString());});
		return list;
    }
    
    @GetMapping("/customer/{id}")
    public List<Customer> getCustomer(@PathVariable int id) {
    	List<Customer> list =  new ArrayList<>();
		template.query(
                "SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> {list .add(customer);System.out.println(customer.toString());});
		return list;
    }
    
    @PutMapping("/customer")
    public List<Customer> updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        return new ArrayList<>();
    }
    
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id) {
    	
    }

}
