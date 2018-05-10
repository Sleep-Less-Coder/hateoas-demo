package com.hemant.restservices.hateoas.demo;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.model.Order;
import com.hemant.restservices.hateoas.demo.repository.CustomerRepository;
import com.hemant.restservices.hateoas.demo.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.hemant.restservices.hateoas.demo.repository")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository, OrderRepository orderRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Logger log = LoggerFactory.getLogger(Application.class);
				log.info("####### Saving some customers from CommandLineRunner #######");

			    customerRepository.save(new Customer("Hemant", "Capital One"));
				customerRepository.save(new Customer("Deepak", "Delliotte"));
				customerRepository.save(new Customer("Travis", "Wells Fargo"));
				customerRepository.save(new Customer("Neilson", "Google"));

				log.info("####### 4 customers saved! #######");
				log.info("####### Saving some Orders for Customer ID 1");

				// Save some orders
                Order order1 = new Order(1, 20.99D, 2);
                order1.setCustomer(customerRepository.findById(1).get());
                orderRepository.save(order1);

                Order order2 = new Order(2, 30.99D, 5);
                order2.setCustomer(customerRepository.findById(1).get());
                orderRepository.save(order2);
				log.info("####### Saved! Done with CommandLineRunner #######");
			}
		};
	}
}
