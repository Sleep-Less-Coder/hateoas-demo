package com.hemant.restservices.hateoas.demo.controller;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.model.Order;
import com.hemant.restservices.hateoas.demo.service.CustomerService;
import com.hemant.restservices.hateoas.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();

        for (Customer customer : allCustomers) {
            Link selfLink = linkTo(CustomerController.class).slash(customer.getCustomerId()).withSelfRel();
            customer.add(selfLink);

            if (orderService.findAllOrdersOfCustomer(customer).size() > 0) {
                List<Order> methodLinkBuilder =
                        methodOn(CustomerController.class).getAllOrdersByCustomer(customer.getCustomerId());
                Link ordersLink = linkTo(methodLinkBuilder).withRel("allOrders");
                customer.add(ordersLink);
            }
        }
        return allCustomers;
    }

    @GetMapping(value = "{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") int customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if(customer.isPresent()) {
            Link selfRel = linkTo(CustomerController.class).slash(customer.get().getCustomerId()).withSelfRel();
            customer.get().add(selfRel);
        }
        return customer;
    }

    @GetMapping(value = "{customerId}/orders")
    public List<Order> getAllOrdersByCustomer(@PathVariable("customerId") int customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if(customer.isPresent()) {
            Link selfRel = linkTo(CustomerController.class).slash(customer.get().getCustomerId()).withSelfRel();
            customer.get().add(selfRel);
        }

        List<Order> allOrdersOfCustomer = orderService.findAllOrdersOfCustomer(customer.get());
        return allOrdersOfCustomer;

    }

}
