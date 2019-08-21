package com.codelove.cracker.ndbapi.searchAPI.responseTypes;

import com.codelove.cracker.ndbapi.common.NDBAPIErrors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchAPIResponse {

    @JsonProperty("list")
    private Result result;

    public Result getResult() {
        return result;
    }

    @JsonProperty("errors")
    private NDBAPIErrors ndbapiErrors;

    public void setResult(Result result) {
        this.result = result;
    }

    public NDBAPIErrors getNdbapiErrors() {
        return ndbapiErrors;
    }

    public void setNdbapiErrors(NDBAPIErrors ndbapiErrors) {
        this.ndbapiErrors = ndbapiErrors;
    }
}
