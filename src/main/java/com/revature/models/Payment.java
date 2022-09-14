package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="userid")
    private int userId;

    @Column(name="credit_card_number")
    private String creditCardNumber;

    @Column(name="security_code")
    private String securityCode;

    @Column(name="expiration")
    private String expiration;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", expDate='" + expiration + '\'' +
                '}';
    }
}
