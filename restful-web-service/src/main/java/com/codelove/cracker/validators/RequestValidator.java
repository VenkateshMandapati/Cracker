package com.codelove.cracker.validators;

import com.codelove.cracker.errors.RequestError;
import com.codelove.cracker.exception.InvalidRequestException;
import com.codelove.cracker.service.ICalorieTrackerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

	public static boolean isCustomerValid(Integer customerId, ICalorieTrackerService calorieTrackerService) {

		if(customerId <= 0 || calorieTrackerService.getCustomerById(customerId) == null){
			RequestError requestError = new RequestError();
			requestError.setFieldName("customerId");
			requestError.setErrorMessage("Invalid Customer Id");
			List<RequestError> errorList = new ArrayList<>();
			errorList.add(requestError);
			throw new InvalidRequestException(errorList, HttpStatus.BAD_REQUEST);
       }

		return true;
	}

}
