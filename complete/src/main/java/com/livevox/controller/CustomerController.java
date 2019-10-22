package com.livevox.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.livevox.Customer;

@RestController
public class CustomerController {

	public class CustomerRowMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
		}
	}

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	JdbcTemplate template;

	@GetMapping("/customer/list")
	public List<Customer> listCustomers(HttpServletRequest request, HttpServletResponse response) {
		List<Customer> list = new ArrayList<>();
		template.query("SELECT id, first_name, last_name FROM customers", new CustomerRowMapper()).forEach(customer -> {
			list.add(customer);
			log.debug(customer.toString());
		});
		return list;
	}

	@GetMapping("/customer/{id}")
	public List<Customer> getCustomer(@PathVariable long id) {
		List<Customer> list = new ArrayList<>();
		template.query("SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[] { id },
				new CustomerRowMapper()).forEach(customer -> {
					list.add(customer);
					log.debug(customer.toString());
				});
		return list;
	}

	@PutMapping("/customer")
	public void updateCustomer(@RequestBody Customer updateInfo) {
		long id = updateInfo.getId();
		List<Customer> list = getCustomer(id);
		if (list.size() > 0) {
			Customer theCustomer = list.iterator().next();
			if(!updateInfo.getFirstName().isEmpty()) {
				theCustomer.setFirstName(updateInfo.getFirstName());
			}
			if(!updateInfo.getLastName().isEmpty()) {
				theCustomer.setLastName(updateInfo.getLastName());
			}
			template.update("UPDATE customers SET first_name = ?,last_name=? WHERE id = ?", new Object[] { theCustomer.getFirstName(), theCustomer.getLastName(), id });
		}

	}

	@PostMapping("/customer")
	public void createCustomer(@RequestBody Customer cust) {
		template.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)",
				new Object[] { cust.getFirstName(), cust.getLastName() });
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable long id) {
		int res = template.update("DELETE FROM customers WHERE id = ?", new Object[] { id });
		if (res > 0) {
			log.info("Deleted user with id " + id);
		} else {
			log.info("No record found for id " + id);
		}
	}

}
