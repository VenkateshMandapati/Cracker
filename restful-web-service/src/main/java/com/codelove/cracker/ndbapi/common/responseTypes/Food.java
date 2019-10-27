package com.codelove.cracker.ndbapi.common.responseTypes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Food {

	private String ndbno;

	private String name;

	private Double weight;

	private String measure;

	private List<Nutrient> nutrients;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getNdbno() {
		return ndbno;
	}

	public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Nutrient> getNutrients() {
		return nutrients;
	}

	public void setNutrients(List<Nutrient> nutrients) {
		this.nutrients = nutrients;
	}

	public Food() {
		super();
	}

}
