package com.codelove.cracker.platform.REST;

import com.codelove.cracker.ndbapi.common.NDBAPIErrors;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class RestTemplateErrorResponseErrorHandler implements ResponseErrorHandler {

    private String errorResponse;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {

        if(checkIfErrorResponse(clientHttpResponse.getBody()) && clientHttpResponse.getStatusCode() == HttpStatus.OK){
            return true;
        }
        
        if(clientHttpResponse.getStatusCode() != HttpStatus.OK) {
        	return true;
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, null, errorResponse.getBytes(), null);
    }

    private boolean checkIfErrorResponse(InputStream responseInputStream) throws IOException {
        String responseString = new BufferedReader(new InputStreamReader(responseInputStream))
                .lines().collect(Collectors.joining("\n"));
        
        errorResponse = responseString;
        NDBAPIErrors ndbAPIErrors = mapResponseToNDBErrors(responseString);

        if(ndbAPIErrors.getHttpStatus() == 400){
            return true;
        }

        return false;
    }
    
    private NDBAPIErrors mapResponseToNDBErrors(String errorResponse) throws JsonParseException, JsonMappingException, IOException {
    	return new ObjectMapper().readValue(errorResponse, NDBAPIErrors.class);
    }
}
