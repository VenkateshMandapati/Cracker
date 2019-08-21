package com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes;

import java.util.List;

import com.codelove.cracker.ndbapi.common.responseTypes.Food;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NutrientReport {

	List<Food> foods;

	//List<Group> groups;

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

//    public List<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }

    public NutrientReport() {
		super();
	}

}
