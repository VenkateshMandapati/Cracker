package com.codelove.cracker.ndbapi.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.http.HttpStatus;

@JsonDeserialize(using = CustomErrorMapper.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NDBAPIErrors {

    private int httpStatus;

    private String message;

//    @JsonProperty("errors")
//    private NDBErrors ndbErrors;
//
//    public NDBAPIErrors() {
//    }
//
//    public NDBErrors getNdbErrors() {
//        return ndbErrors;
//    }
//
//    public void setNdbErrors(NDBErrors ndbErrors) {
//        this.ndbErrors = ndbErrors;
//    }


    public NDBAPIErrors() {
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
