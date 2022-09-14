package com.revature.services;

import com.revature.dtos.ProfileInfo;
import com.revature.models.Address;
import com.revature.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Optional<Address> findByUserid(int id){
        return addressRepository.findByUserid(id);
    }

    public Optional<Address> findById(int id){
        return addressRepository.findById(id);
    }

    public Address save(int id, ProfileInfo profileInfo){

        Optional<Address> retrieved = addressRepository.findByUserid(id);

        if(retrieved.isPresent()){

            Address updated = retrieved.get();
            updated.setAddress1(profileInfo.getAddress1());
            updated.setAddress2(profileInfo.getAddress2());
            updated.setCity(profileInfo.getCity());
            updated.setState(profileInfo.getState());
            updated.setZip(profileInfo.getZip());
            updated.setCountry(profileInfo.getCountry());

            return addressRepository.save(updated);

        }else{

            Address created = new Address();
            created.setId(0);
            created.setUserid(id);
            created.setAddress1(profileInfo.getAddress1());
            created.setAddress2(profileInfo.getAddress2());
            created.setCity(profileInfo.getCity());
            created.setState(profileInfo.getState());
            created.setZip(profileInfo.getZip());
            created.setCountry(profileInfo.getCountry());

            return  addressRepository.save(created);
        }


    }

}
