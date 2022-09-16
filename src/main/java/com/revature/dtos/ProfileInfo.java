package com.revature.dtos;

import com.revature.models.Address;
import com.revature.models.Payment;
import com.revature.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInfo {

    User user;
    Payment payment;
    Address address;

}