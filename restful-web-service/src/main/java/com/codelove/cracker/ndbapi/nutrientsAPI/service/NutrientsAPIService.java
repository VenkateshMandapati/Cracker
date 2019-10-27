package com.codelove.cracker.ndbapi.nutrientsAPI.service;

import com.codelove.cracker.exception.NDBAPIException;
import com.codelove.cracker.ndbapi.common.APIConnectionDetails;
import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.FoodWithNutrientsResponse;
import com.codelove.cracker.platform.REST.IRESTAPIConnector;
import com.codelove.cracker.utils.CrackerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.List;


@Service
public class NutrientsAPIService extends APIConnectionDetails implements INutrientsAPIService {

    @Value("${ndb.rest.api.nutrients}")
    private String nutrients;

    private IRESTAPIConnector restAPIConnector;

    @Autowired
    public NutrientsAPIService(IRESTAPIConnector restAPIConnector) {
        this.restAPIConnector = restAPIConnector;
    }

    @Override
	public FoodWithNutrientsResponse getNutrientsInfoForFood(String ndbNo, String foodGroupId, List<String> nutrientIds) {

        if(nutrientIds == null || nutrientIds.isEmpty()){
            throw new NDBAPIException("The nutrientId list cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        String nutrientsAPIurl = buildNutrientsAPIurl(ndbNo, foodGroupId, nutrientIds);
        ResponseEntity<FoodWithNutrientsResponse> nutrientAPIResponse=null;

        try{
            nutrientAPIResponse = restAPIConnector.getResponseEntityForhttpGET(nutrientsAPIurl, FoodWithNutrientsResponse.class);

            if(nutrientAPIResponse.getStatusCode() != HttpStatus.OK){
                throw new NDBAPIException("Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        catch(HttpClientErrorException ex){
            throw new NDBAPIException(CrackerUtils.getErrorMessageFromBackendAPIException(ex), ex.getStatusCode());
        }
        catch(RestClientException ex){
            throw new NDBAPIException("Invalid Request", ex, HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex){
            throw new NDBAPIException("Unknown Error", ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return nutrientAPIResponse.getBody();
    }

    private String buildNutrientsAPIurl(String ndbno, String foodGroupId, List<String> nutrientIds) {

        StringBuilder sb = new StringBuilder(super.getUrl());

        sb.append(nutrients).append("api_key=").append(super.getApiKey());

        nutrientIds.stream()
                .forEach(nutrientId -> sb.append("&nutrients=").append(nutrientId));

        if (ndbno != null) {
            sb.append("&ndbno=").append(ndbno);
        }

        if (foodGroupId != null) {
            sb.append("&fg=").append(foodGroupId);
        }

        return sb.toString();

    }
}
