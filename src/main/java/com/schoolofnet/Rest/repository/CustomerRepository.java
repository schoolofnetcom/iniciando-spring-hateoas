package com.schoolofnet.Rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.schoolofnet.Rest.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
