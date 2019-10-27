package com.codelove.cracker.dao;

import com.codelove.cracker.entity.FoodLogEntry;
import com.codelove.cracker.responseTypes.FoodDetailsPerDay;
import com.codelove.cracker.responseTypes.FoodDetailsResponse;
import com.codelove.cracker.responseTypes.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CommonDaoRepositoryImpl implements CommonDaoRepository{

    EntityManager entityManager;

    public CommonDaoRepositoryImpl() {
    }

    @Autowired
    public CommonDaoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<FoodLogEntry> getFoodDetailsForDateRange(int customerId, String startDate, String endDate) {

        String sqlQuery = "Select fl from FoodLogEntry fl Where fl.customerId=:customerId " +
                "and fl.day BETWEEN :startDate AND :endDate order by fl.day asc";

        TypedQuery<FoodLogEntry> foodDetails =
                entityManager.createQuery(sqlQuery, FoodLogEntry.class);
        foodDetails.setParameter("customerId", customerId);
        foodDetails.setParameter("startDate", LocalDate.parse(startDate));
        foodDetails.setParameter("endDate", LocalDate.parse(endDate));

        return foodDetails.getResultList();
    }

    @Override
    public List<FoodLogEntry> getFoodDetailsForSingleDay(int customerId, String day) {
    	LocalDate date = LocalDate.parse(day);
        String sqlQuery = "Select fl from FoodLogEntry fl Where fl.customerId=:customerId "
        		+ "and fl.day=:day";

        TypedQuery<FoodLogEntry> foodDetails =
                entityManager.createQuery(sqlQuery, FoodLogEntry.class);
        foodDetails.setParameter("customerId", customerId);
        foodDetails.setParameter("day", date);

        return foodDetails.getResultList();
    }
}
