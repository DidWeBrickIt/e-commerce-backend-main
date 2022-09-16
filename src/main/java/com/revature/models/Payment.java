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
    private int userid;

    @Column(name="credit_card_number")
    private String credit_card_number;

    @Column(name="security_code")
    private String security_code;

    @Column(name="expiration")
    private String expiration;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
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
                ", userId=" + userid +
                ", creditCardNumber='" + credit_card_number + '\'' +
                ", securityCode='" + security_code + '\'' +
                ", expDate='" + expiration + '\'' +
                '}';
    }
}
