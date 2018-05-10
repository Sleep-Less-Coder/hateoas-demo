package com.hemant.restservices.hateoas.demo.service;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Optional<Customer> getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Transactional
    public Customer getCustomerByCustomerName(String customerName) {
        return customerRepository.findByCustomerName(customerName);
    }
}
