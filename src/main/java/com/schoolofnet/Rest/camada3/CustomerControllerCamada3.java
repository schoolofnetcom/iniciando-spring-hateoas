package com.schoolofnet.Rest.camada3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.schoolofnet.Rest.model.Customer;
import com.schoolofnet.Rest.repository.CustomerRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/customers")
public class CustomerControllerCamada3 {

	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerControllerCamada3(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// capture
	@GetMapping
	@ResponseBody
	public List<Customer> findAll() {
		Iterable<Customer> list = this.customerRepository.findAll();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		for (Customer customer : list) {
			Long id = customer.getItemId();
			
			customer.add(linkTo(methodOn(CustomerControllerCamada3.class).findOne(id)).withSelfRel());
			customers.add(customer);
		}
		
		return customers;		
	}
	
	// capture
	@GetMapping("{id}")
	@ResponseBody
	public Customer findOne(@PathVariable("id") Long id) {
		Customer customer = this.customerRepository.findOne(id);
		
		customer.add(linkTo(methodOn(CustomerControllerCamada3.class).findAll()).withRel("Find All Customers"));
		
		return customer;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Customer create(@RequestBody Customer customer) {
		Customer customerCreated = this.customerRepository.save(customer);
		
		customerCreated.add(linkTo(methodOn(CustomerControllerCamada3.class).findOne(customerCreated.getItemId())).withSelfRel());
		
		return customerCreated;
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		Customer customerExists = this.customerRepository.findOne(id);
		
		if (customerExists != null) {
			customer.setItemId(id);
			
			this.customerRepository.save(customer);
		}
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("id") Long id) {
		this.customerRepository.delete(id);
	}
}
