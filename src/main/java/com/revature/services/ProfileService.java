package com.revature.services;

import com.revature.dtos.ProfileInfo;
import com.revature.models.Address;
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

    public ProfileInfo save(int id, ProfileInfo profileInfo){
        Optional<User> originalUser = this.userService.findById(id);
        if(originalUser.isPresent()){
            User updatedUser = originalUser.get();
            updatedUser.setFirstName(profileInfo.getUser().getFirstName());
            updatedUser.setLastName(profileInfo.getUser().getLastName());
            updatedUser.setImageurl(profileInfo.getUser().getImageurl());
            updatedUser.setEmail(profileInfo.getUser().getEmail());
            updatedUser.setPassword(profileInfo.getUser().getPassword());
            this.userService.save(updatedUser);
        }

        Optional<Address> originalAddress = this.addressService.findByUserid(id);
        if(originalAddress.isPresent()){
            Address updatedAddress = profileInfo.getAddress();
            updatedAddress.setId(originalAddress.get().getId());
            updatedAddress.setUserid(id);
            this.addressService.save(updatedAddress);
        }else{
            Address newAddress = profileInfo.getAddress();
            newAddress.setUserid(id);
            this.addressService.save(newAddress);
        }

        return profileInfo;
    }

    public ProfileInfo get(int id){

        Optional<User> user = userService.findById(id);
        Optional<Address> address = addressService.findByUserid(id);

        ProfileInfo retrieved = new ProfileInfo();

        if(user.isPresent()){
            retrieved.setUser(user.get());
        }
        if(address.isPresent()){
            retrieved.setAddress(address.get());
        }
            return retrieved;
    }
}
