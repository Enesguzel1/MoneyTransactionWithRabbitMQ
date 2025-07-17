package org.example.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String firstName;
    private double balance;
    private String lastNotification;
}
