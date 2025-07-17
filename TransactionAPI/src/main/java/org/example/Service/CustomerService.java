package org.example.Service;

import org.example.DTO.CustomerDTO;
import org.example.Entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO getCustomer(String id);
    void delete(String id);
    List<CustomerDTO> getAllCustomers();
}
