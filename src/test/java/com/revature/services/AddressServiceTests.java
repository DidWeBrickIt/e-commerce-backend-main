package com.revature.services;

import com.revature.models.Address;
import com.revature.repositories.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AddressServiceTests {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void create_address(){
        Address address = new Address(0, 1, "address", "address", "city", "state", 1, "country");
        Assertions.assertNotNull(this.addressRepository.save(address));
    }

    @Test
    void findByUserId_test(){
        Address address = new Address(0, 1, "address", "address", "city", "state", 1, "country");
        Assertions.assertNotNull(this.addressRepository.findByUserid(1));
    }
}
