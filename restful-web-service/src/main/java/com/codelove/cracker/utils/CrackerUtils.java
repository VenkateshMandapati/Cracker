package com.codelove.cracker.utils;

import com.codelove.cracker.exception.CrackerException;
import com.codelove.cracker.ndbapi.common.NDBAPIErrors;
import com.codelove.cracker.ndbapi.common.NDBError;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CrackerUtils {

    private <T> Optional<T> getEmptyOptional(List<T> anyList){
        return Optional.empty();
    }

    public static String getErrorMessageFromBackendAPIException(HttpClientErrorException ex){

        NDBAPIErrors ndbAPIErrors=null;
        try {
            ndbAPIErrors = new ObjectMapper().readValue(ex.getResponseBodyAsString(), NDBAPIErrors.class);
        } catch (JsonParseException e) {
            throw new CrackerException("Error While De-serialization of NDB API Response to JAVA Object", e, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonMappingException e) {
            throw new CrackerException("Error While De-serialization of NDB API Response to JAVA Object", e, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            throw new CrackerException("Error While De-serialization of NDB API Response to JAVA Object", e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ndbAPIErrors.getMessage();
//
//        Optional<NDBAPIErrors> ndbAPIErrorsOptional = Optional.ofNullable(ndbAPIErrors);
//
//        return ndbAPIErrorsOptional
//                .map(NDBAPIErrors::getNdbErrors)
//                .filter(Objects::nonNull)
//                .map(ndbErrors -> {
//                    if(ndbErrors.getErrorList() == null){
//                        return null;
//                    }
//
//                    return ndbErrors.getErrorList().stream().findFirst().orElse(null);
//                })
//                .map(NDBError::getMessage)
//                .orElse("Unknown Error");
    }
}
