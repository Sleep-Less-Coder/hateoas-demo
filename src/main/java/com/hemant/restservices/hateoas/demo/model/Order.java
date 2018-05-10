package com.hemant.restservices.hateoas.demo.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private double price;
    private int quantity;

    @ManyToOne @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Order(int orderId, double price, int quantity) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
