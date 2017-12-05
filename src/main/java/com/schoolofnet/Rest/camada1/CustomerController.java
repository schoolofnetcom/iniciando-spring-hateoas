package com.schoolofnet.Rest.camada1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolofnet.Rest.model.Customer;
import com.schoolofnet.Rest.repository.CustomerRepository;

@RestController
@RequestMapping("/camada1")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@PostMapping("{id}")
	public Customer show(@PathVariable("id") Long id) {
		return this.customerRepository.findOne(id);
	}
	
	@PostMapping
	@ResponseBody
	public Customer create(@RequestBody Customer customer) {
		return this.customerRepository.save(customer);
	}
}
