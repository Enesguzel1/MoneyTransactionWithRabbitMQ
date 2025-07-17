package org.example.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.CustomerDTO;
import org.example.Entity.Customer;
import org.example.Repository.CustomerRepository;
import org.example.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setBalance(customerDTO.getBalance());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setId(customerDTO.getId());
        customer.setLastNotification(customerDTO.getLastNotification());
        try{
            customerRepository.save(customer);
            return  customerDTO;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        return null;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastNotification(customer.getLastNotification());
            customerDTO.setBalance(customer.getBalance());
            customerDTOS.add(customerDTO);

        });
        return customerDTOS;
    }
}
