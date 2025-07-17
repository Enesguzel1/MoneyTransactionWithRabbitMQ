package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.CustomerDTO;
import org.example.DTO.TransactionDTO;
import org.example.Producer.TransactionProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionProducer transactionProducer;
    @PostMapping
    public ResponseEntity<String> CreateCustomer(@RequestBody TransactionDTO transactionDTO) {
        transactionProducer.StartTransaction(transactionDTO);
        return ResponseEntity.ok().body("İşlem Başarıyla Sıraya Alındı");
    }
}
