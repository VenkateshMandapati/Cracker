package com.codelove.cracker.dao;

import java.util.List;

import com.codelove.cracker.entity.FoodLogEntry;
import com.codelove.cracker.responseTypes.FoodDetailsResponse;

public interface CommonDaoRepository {

	List<FoodLogEntry> getFoodDetailsForDateRange(int customerId,
                                                   String startDate, String endDate);

    List<FoodLogEntry> getFoodDetailsForSingleDay(int customerId, String day);
}
