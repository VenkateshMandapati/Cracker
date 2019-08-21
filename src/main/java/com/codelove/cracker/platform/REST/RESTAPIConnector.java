package com.codelove.cracker.platform.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RESTAPIConnector implements IRESTAPIConnector{

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    public RESTAPIConnector(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder
//                .errorHandler(new RestTemplateErrorResponseErrorHandler())
//                .build();
//    }

    @Override
    public <T> ResponseEntity<T> getResponseEntityForhttpGET(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }

    @Override
    public <T> T getPOJOForhttpGET(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }


}
