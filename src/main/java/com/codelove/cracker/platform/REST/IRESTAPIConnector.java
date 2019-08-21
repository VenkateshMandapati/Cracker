package com.codelove.cracker.platform.REST;

import org.springframework.http.ResponseEntity;

public interface IRESTAPIConnector {

    <T> ResponseEntity<T> getResponseEntityForhttpGET(String url, Class<T> responseType);

    <T> T getPOJOForhttpGET(String url, Class<T> responseType);
}
