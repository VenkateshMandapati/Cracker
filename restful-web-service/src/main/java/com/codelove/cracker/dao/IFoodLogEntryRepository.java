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

    @Query("Select f.id From FoodLogEntry f where f.customerId=:id")
    List<Integer> findAllLogEntryIdForCustomer(@Param("id") Integer id);
}
