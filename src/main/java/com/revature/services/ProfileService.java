package com.revature.services;

import com.revature.dtos.ProfileInfo;
import com.revature.models.Address;
import com.revature.models.Payment;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    AddressService addressService;
    @Autowired
    UserService  userService;
    @Autowired
    PaymentService paymentService;

    public ProfileInfo save(int id, ProfileInfo profileInfo){

        Optional<User> originalUser = this.userService.findById(id);
        if(originalUser.isPresent()){
            User updatedUser = originalUser.get();
            updatedUser.setFirstName(profileInfo.getUser().getFirstName());
            updatedUser.setLastName(profileInfo.getUser().getLastName());
            this.userService.save(updatedUser);
        }

        Optional<Address> originalAddress = this.addressService.findByUserid(id);
        if(originalAddress.isPresent()){
            Address updatedAddress = profileInfo.getAddress();
            updatedAddress.setId(originalAddress.get().getId());
            updatedAddress.setUserid(id);
            this.addressService.save(updatedAddress);
        }

        Optional<Payment> originalPayment = this.paymentService.findByUserid(id);
        if(originalPayment.isPresent()){
            Payment updatedPayment = profileInfo.getPayment();
            updatedPayment.setId(originalPayment.get().getId());
            updatedPayment.setUserid(id);
            this.paymentService.save(updatedPayment);
        }


        return profileInfo;
    }

    public ProfileInfo get(int id){

        Optional<User> user = userService.findById(id);
        Optional<Address> address = addressService.findByUserid(id);
        Optional<Payment> payment = paymentService.findByUserid(id);

        ProfileInfo retrieved = new ProfileInfo();

        if(user.isPresent()){
            retrieved.setUser(user.get());
        }
        if(address.isPresent()){
            retrieved.setAddress(address.get());
        }
        if(payment.isPresent()){
            retrieved.setPayment(payment.get());
        }
            return retrieved;
    }
}
