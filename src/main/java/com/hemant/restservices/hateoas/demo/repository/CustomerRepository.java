package com.hemant.restservices.hateoas.demo.repository;

import com.hemant.restservices.hateoas.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByCustomerName(String customerName);
}
