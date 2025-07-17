package org.example.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionDTO implements Serializable {
    private String fromCustomerId;
    private String toCustomerId;
    private double Amount;
}
