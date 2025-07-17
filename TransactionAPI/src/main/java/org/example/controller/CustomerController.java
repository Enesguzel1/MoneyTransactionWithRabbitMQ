package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.CustomerDTO;
import org.example.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<CustomerDTO> AddCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.save(customerDTO);
        return ResponseEntity.ok().body(customerDTO);
    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> GetAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

}
