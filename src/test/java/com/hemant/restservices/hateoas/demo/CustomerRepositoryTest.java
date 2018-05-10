package com.hemant.restservices.hateoas.demo;

import com.hemant.restservices.hateoas.demo.model.Customer;
import com.hemant.restservices.hateoas.demo.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CustomerRepository customerRepository;

    //now the test cases
    @Test
    public void whenFindByName_thenReturnCustomer() {

        Customer john = new Customer("John", "Oracle");

        testEntityManager.persist(john);
        testEntityManager.flush();

        Customer found = customerRepository.findByCustomerName(john.getCustomerName());

        assertThat(found.getCustomerName()).isEqualTo(john.getCustomerName());
    }

}
