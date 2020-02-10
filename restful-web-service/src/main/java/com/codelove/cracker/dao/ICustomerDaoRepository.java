package com.codelove.cracker.dao;


import com.codelove.cracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerDaoRepository extends JpaRepository<Customer, Integer> {

    //below methods are provided free by spring jpa

    //findById()

    //findAll()

    //save()

    //deleteByID()

    @Query("Select c From Customer c where c.email=:email")
    Customer findByEmail(@Param("email") String email);
}
