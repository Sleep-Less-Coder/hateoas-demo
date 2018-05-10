package com.hemant.restservices.hateoas.demo.service;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.model.Order;
import com.hemant.restservices.hateoas.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAllOrdersOfCustomer(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }
}
