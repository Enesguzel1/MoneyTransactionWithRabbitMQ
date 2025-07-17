package org.example.Repository;

import org.example.Entity.Customer;
import org.example.Service.CustomerService;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
