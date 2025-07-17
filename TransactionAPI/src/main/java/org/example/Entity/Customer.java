package org.example.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Customer {
    @Id
    private String id;
    private String firstName;
    private double balance;
    private String lastNotification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLastNotification() {
        return lastNotification;
    }

    public void setLastNotification(String lastNotification) {
        this.lastNotification = lastNotification;
    }
}
