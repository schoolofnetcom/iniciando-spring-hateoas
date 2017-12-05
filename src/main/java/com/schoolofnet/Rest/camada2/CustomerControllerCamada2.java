package com.schoolofnet.Rest.camada2;

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

@RestController
@RequestMapping("/camada2")
public class CustomerControllerCamada2 {

	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerControllerCamada2(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// capture
	@GetMapping
	public List<Customer> findAll() {
		return (List<Customer>) this.customerRepository.findAll();
	}
	
	// capture
	@GetMapping("{id}")
	public Customer findOne(@PathVariable("id") Long id) {
		return this.customerRepository.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Customer create(@RequestBody Customer customer) {
		return this.customerRepository.save(customer);
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
