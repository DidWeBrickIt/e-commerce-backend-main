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

    public Address save(Address address){
    return addressRepository.save(address);
    }

}
