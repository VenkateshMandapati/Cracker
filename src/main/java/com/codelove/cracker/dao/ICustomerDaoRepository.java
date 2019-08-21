package com.codelove.cracker.dao;


import com.codelove.cracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDaoRepository extends JpaRepository<Customer, Integer> {

    //below methods are provided free by spring jpa

    //findById()

    //findAll()

    //save()

    //deleteByID()
}
