package com.codelove.cracker.ndbapi.common;

import org.springframework.beans.factory.annotation.Value;

public class APIConnectionDetails {

    @Value("${ndb.rest.url}")
    private String url;

    @Value("${ndb.rest.api.key}")
    private String apiKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
