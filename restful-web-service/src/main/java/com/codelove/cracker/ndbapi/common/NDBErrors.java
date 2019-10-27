package com.codelove.cracker.ndbapi.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NDBErrors {

	@JsonProperty("error")
    private List<NDBError> errorList;

    public NDBErrors() {
    }

    public List<NDBError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<NDBError> errorList) {
        this.errorList = errorList;
    }
}
