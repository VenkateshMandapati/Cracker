package com.codelove.cracker.dao;

import com.codelove.cracker.entity.FoodLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFoodLogEntryRepository extends JpaRepository<FoodLogEntry, Integer>, CommonDaoRepository{

    //below methods are provided free by spring jpa

    //findById()

    //findAll()

    //save()

    //deleteByID()

    @Query(nativeQuery=true, value= "Select * From food_storage f where f.customer_id=:id ORDER BY time_stamp DESC LIMIT 1")
    FoodLogEntry getCustomerLastLoggedFoodDetails(@Param("id") Integer id);

    //JPQL, which is spring jpa query language
    @Query("Select f.id From FoodLogEntry f where f.customerId=:id")
    List<Integer> findAllLogEntryIdForCustomer(@Param("id") Integer id);


}
