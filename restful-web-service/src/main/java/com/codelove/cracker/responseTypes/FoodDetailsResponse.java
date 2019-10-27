package com.codelove.cracker.responseTypes;

import java.util.List;

public class FoodDetailsResponse {

    private Integer customerId;

    private List<FoodDetailsPerDay> foodDetails;

    public FoodDetailsResponse() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<FoodDetailsPerDay> getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(List<FoodDetailsPerDay> foodDetails) {
        this.foodDetails = foodDetails;
    }

    @Override
    public String toString() {
        return "FoodDetailsResponse{" +
                "customerId=" + customerId +
                ", foodDetails=" + foodDetails +
                '}';
    }
}
