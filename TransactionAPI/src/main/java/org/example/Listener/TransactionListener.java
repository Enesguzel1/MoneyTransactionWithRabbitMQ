package org.example.Listener;

import org.example.DTO.TransactionDTO;
import org.example.Entity.Customer;
import org.example.Repository.CustomerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionListener {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    CustomerRepository customerRepository;
    @Value("${eg-bank-rabbit-exchange-name}")
    private String exchangeName;

    @RabbitListener(queues = "${eg-bank-rabbit-queue1-name}")
    public void firstQueueListener(TransactionDTO transactionDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(transactionDTO.getToCustomerId());
        if (customerOptional.isPresent()) {
            System.out.println("First Queue Listener : Gönderilecek Hesap Bulunmuştur işlem gerçekleşebilir. Mesaj 2. Kuyruğda Yönlendiriliyor ");
            rabbitTemplate.convertAndSend(exchangeName, "secondRoute", transactionDTO);
        }
        else{
            System.out.println("Göndereceğiniz Kişi Bulunamamıştır Tekrar Deneyiniz!");
        }
    }
    @RabbitListener(queues = "secondStepQueue")
    public void secondQueueListener(TransactionDTO transactionDTO) {
        Optional<Customer> fromcustomerOptional = customerRepository.findById(transactionDTO.getFromCustomerId());
        Optional<Customer> tocustomerOptional = customerRepository.findById(transactionDTO.getToCustomerId());
        if (fromcustomerOptional.isPresent() && tocustomerOptional.isPresent()) {
            Customer fromcustomer = fromcustomerOptional.get();
            Customer tocustomer = tocustomerOptional.get();
            fromcustomer.setBalance(fromcustomer.getBalance() - transactionDTO.getAmount());
            tocustomer.setBalance(tocustomer.getBalance() + transactionDTO.getAmount());
            customerRepository.save(fromcustomer);
            customerRepository.save(tocustomer);
            System.out.println("Second Queue Listener: Aktarımı gerçekleştirdi. Notification Servisi için 3. kuyruğa gönderiyor");
            rabbitTemplate.convertAndSend(exchangeName, "thirdRoute", transactionDTO);
        }
    }
    @RabbitListener(queues = "thirdStepQueue")
    public void thirdQueueListener(TransactionDTO transactionDTO) {
        Optional<Customer> fromcustomerOptional = customerRepository.findById(transactionDTO.getFromCustomerId());
        Optional<Customer> tocustomerOptional = customerRepository.findById(transactionDTO.getToCustomerId());
        if (fromcustomerOptional.isPresent() && tocustomerOptional.isPresent()) {
            Customer fromcustomer = fromcustomerOptional.get();
            Customer tocustomer = tocustomerOptional.get();
            System.out.println("Third Queue Listener(Notification Service): \n Aktarım Gerçekleşti!!! \n Aktaran Kişi : " + fromcustomer.getFirstName() + "\n Aktarılan Kişi : " + tocustomer.getFirstName());
        }

    }
}
