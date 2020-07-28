package com.movie.ratings.service;

import com.movie.ratings.model.Customer;
import com.movie.ratings.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Optional<Customer> getCustomer(Integer customerId) {
        return this.customerRepository.findById(customerId);
    }
}
