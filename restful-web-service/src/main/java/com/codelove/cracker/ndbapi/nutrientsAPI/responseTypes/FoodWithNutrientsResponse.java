package com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes;

import com.codelove.cracker.ndbapi.common.NDBAPIErrors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodWithNutrientsResponse{

    @JsonProperty("report")
	private NutrientReport nutrientReport;
    
    @JsonProperty("errors")
    private NDBAPIErrors ndbAPIErrors;

    //private List<Group> groups;

	public NutrientReport getNutrientReport() {
		return nutrientReport;
	}

	public void setNutrientReport(NutrientReport report) {
		this.nutrientReport = report;
	}

//    public List<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }

    public FoodWithNutrientsResponse() {
		super();
	}

}
