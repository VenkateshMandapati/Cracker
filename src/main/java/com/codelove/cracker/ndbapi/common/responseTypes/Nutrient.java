package com.codelove.cracker.ndbapi.common.responseTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Nutrient {

	@JsonProperty("nutrient_id")
	private String nutrientId;

	private String nutrient;

	@JsonProperty("unit")
	private String measurementUnit;

	private String value;

	@JsonProperty("gm")
	private String hundredGramsEquivalentValue;

	public String getNutrientId() {
		return nutrientId;
	}

	public void setNutrientId(String nutrientId) {
		this.nutrientId = nutrientId;
	}

	public String getNutrient() {
		return nutrient;
	}

	public void setNutrient(String nutrient) {
		this.nutrient = nutrient;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHundredGramsEquivalentValue() {
		return hundredGramsEquivalentValue;
	}

	public void setHundredGramsEquivalentValue(String hundredGramsEquivalentValue) {
		this.hundredGramsEquivalentValue = hundredGramsEquivalentValue;
	}

	public Nutrient() {
		super();
	}

}
