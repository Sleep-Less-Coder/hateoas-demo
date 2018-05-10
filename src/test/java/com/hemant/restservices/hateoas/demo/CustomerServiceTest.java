package com.hemant.restservices.hateoas.demo;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.repository.CustomerRepository;
import com.hemant.restservices.hateoas.demo.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
    }

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer hemant = new Customer("Hemant", "Wells Fargo");
        Mockito.when(customerRepository.findByCustomerName(hemant.getCustomerName())).thenReturn(hemant);
    }

    //now the test cases
    @Test
    public void whenValidName_thenCustomerShouldBeFound() {
        String name = "Hemant";
        Customer found = customerService.getCustomerByCustomerName(name);

        assertThat(found.getCustomerName()).isEqualTo(name);
    }
}
