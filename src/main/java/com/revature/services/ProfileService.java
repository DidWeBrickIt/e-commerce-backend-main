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

        System.out.println("Inside Profile Service");
        System.out.println(profileInfo);

        Optional<User> originalUser = this.userService.findById(id);
        if(originalUser.isPresent()){
            System.out.println("Inside Profile Service Update User");
            User updatedUser = originalUser.get();
            updatedUser.setFirstName(profileInfo.getUser().getFirstName());
            updatedUser.setLastName(profileInfo.getUser().getLastName());
            updatedUser.setImageurl(profileInfo.getUser().getImageurl());
            this.userService.save(updatedUser);
        }

        Optional<Address> originalAddress = this.addressService.findByUserid(id);
        if(originalAddress.isPresent()){
            System.out.println("Inside Profile Service Update Address");
            Address updatedAddress = profileInfo.getAddress();
            updatedAddress.setId(originalAddress.get().getId());
            updatedAddress.setUserid(id);
            this.addressService.save(updatedAddress);
        }else{
            System.out.println("Inside Profile Service Create Address");
            Address newAddress = profileInfo.getAddress();
            newAddress.setUserid(id);
            this.addressService.save(newAddress);
        }

        Optional<Payment> originalPayment = this.paymentService.findByUserid(id);
        if(originalPayment.isPresent()){
            System.out.println("Inside Profile Service Update Payment");
            Payment updatedPayment = profileInfo.getPayment();
            updatedPayment.setId(originalPayment.get().getId());
            updatedPayment.setUserid(id);
            this.paymentService.save(updatedPayment);
        }else{
            System.out.println("Inside Profile Service Create Payment");
            Payment newPayment = profileInfo.getPayment();
            newPayment.setUserid(id);
            System.out.println(profileInfo.getPayment());
            System.out.println(newPayment);
            this.paymentService.save(newPayment);
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
