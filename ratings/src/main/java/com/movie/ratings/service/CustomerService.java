package com.movie.ratings.service;

import com.movie.ratings.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Optional<Customer> getCustomer(Integer customerId);
}
