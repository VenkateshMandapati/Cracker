package com.codelove.cracker.responseTypes;

public class NutrientInfo {

    private String nutrientId;

    private String nutrientName;

    private String unit;

    private String value;

    private String hundredGramEquivalentValue;

    public NutrientInfo() {
    }

    public String getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(String nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHundredGramEquivalentValue() {
        return hundredGramEquivalentValue;
    }

    public void setHundredGramEquivalentValue(String hundredGramEquivalentValue) {
        this.hundredGramEquivalentValue = hundredGramEquivalentValue;
    }
}
