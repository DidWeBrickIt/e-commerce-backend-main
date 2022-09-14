package com.revature.repositories;

import com.revature.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository <Address, Integer> {

    Optional<Address> findByUserid(int id);
    Optional<Address> findById(int id);

}
