package com.codelove.cracker.requestTypes;

import java.time.LocalDate;

import com.codelove.cracker.constraints.CustomerIdValidation;
import com.codelove.cracker.constraints.FoodGroupIdValidation;
import com.codelove.cracker.constraints.PartOfDayValidation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FoodEntryRequest {

    private LocalDate day;

    @PartOfDayValidation
    private String partOfDay;

    @NotNull
    private String foodName;

    @FoodGroupIdValidation
    private String foodGroupId;

    @Min(value=0, message="calories should be greater than or equal to zero")
    private int calories;

    @CustomerIdValidation
    private int customerId;

    @Min(value=0, message="quantity should be greater than zero")
    private int quantity=1;

    public FoodEntryRequest() {
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(String foodGroupId) {
        this.foodGroupId = foodGroupId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FoodEntryRequest{" +
                "day='" + day + '\'' +
                ", partOfDay='" + partOfDay + '\'' +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", customerId=" + customerId +
                '}';
    }
}
