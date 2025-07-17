package org.example.Producer;

import lombok.RequiredArgsConstructor;
import org.example.DTO.TransactionDTO;
import org.example.Entity.Customer;
import org.example.Repository.CustomerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionProducer {
    @Autowired
    CustomerRepository customerRepository;
    @Value("${eg-bank-rabbit-exchange-name}")
    private String exchangeName;
    @Value("${eg-bank-rabbit-routing-name}")
    private String routingName;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void StartTransaction(TransactionDTO transactionDTO) {
        System.out.println("Producer tarafından Transfer işlemi sıraya gönderiliyor...");
        Optional<Customer> customerOptional = customerRepository.findById(transactionDTO.getFromCustomerId());
        customerOptional.ifPresentOrElse(customer -> {
         if (customer.getBalance() > transactionDTO.getAmount()) {
             rabbitTemplate.convertAndSend(exchangeName,routingName,transactionDTO);
         }  else {
             System.out.println("Hesabınızda Yeteri Kadar Para Bulunmamaktadır.");
         }

        },()-> System.out.println("Hesap Bulunamadı"));

        System.out.println("Producer tarafından 1. Sıraya Gönderildi...");
    }
}
