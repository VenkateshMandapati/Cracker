package com.codelove.cracker.ndbapi.searchAPI.service;

import com.codelove.cracker.controllers.BaseController;
import com.codelove.cracker.exception.NDBAPIException;
import com.codelove.cracker.ndbapi.common.APIConnectionDetails;
import com.codelove.cracker.ndbapi.searchAPI.responseTypes.Item;
import com.codelove.cracker.ndbapi.searchAPI.responseTypes.SearchAPIResponse;
import com.codelove.cracker.platform.REST.IRESTAPIConnector;
import com.codelove.cracker.utils.CrackerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Component
public class SearchAPIService extends APIConnectionDetails implements ISearchAPIService {

    private static final Logger logger = LogManager.getLogger(BaseController.class);

    private IRESTAPIConnector restAPIConnector;

    @Value("${ndb.rest.api.search}")
    private String searchURL;

    private static final String QUERY_REQ = "&q=";

    private static final String API_KEY_REQ = "api_key=";

    private static final String FOOD_GROUP_REQ = "&fg=";

    private static final String SORT_REQ = "&sort=";

    private static final String MAX_REQ = "&max=";

    private static final String MAX_RESULTS = "10";

    private static final String SORT_TYPE = "r";

    @Autowired
    public SearchAPIService(IRESTAPIConnector restAPIConnector) {
        this.restAPIConnector = restAPIConnector;
    }

    public IRESTAPIConnector getRestAPIConnector() {
        return restAPIConnector;
    }

    public void setRestAPIConnector(IRESTAPIConnector restAPIConnector) {
        this.restAPIConnector = restAPIConnector;
    }

    @Override
    public String getNDBNumberForFood(String foodName) {

        return getNDBNumberForFoodWithFoodGroup(foodName, null, true);
    }

    @Override
    public String getNDBNumberForFoodWithFoodGroup(String foodName, String foodGroupId, boolean retry) {
        if(foodName == null){
            throw new NDBAPIException("FoodName should not be null", HttpStatus.BAD_REQUEST);
        }

        String searchUrl = buildSearchAPIurl(foodName, foodGroupId);
        ResponseEntity<SearchAPIResponse> searchAPIResponse=null;

        try{
            searchAPIResponse = restAPIConnector.getResponseEntityForhttpGET(searchUrl, SearchAPIResponse.class);

            if(searchAPIResponse.getStatusCode() != HttpStatus.OK){
                throw new NDBAPIException("Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        catch(HttpClientErrorException ex){

            throw new NDBAPIException(CrackerUtils.getErrorMessageFromBackendAPIException(ex), ex.getStatusCode());
        }
        catch(RestClientException ex){
            throw new NDBAPIException("Invalid Request Parameters", ex, HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex){
            throw new NDBAPIException("Unknown Error", ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return getNDBNoFromResponse(searchAPIResponse.getBody());
    }

    private String buildSearchAPIurl(String foodName, String foodGroupId){

        StringBuilder sb = new StringBuilder(super.getUrl());

        sb.append(searchURL).append(API_KEY_REQ).append(super.getApiKey());
        sb.append(QUERY_REQ).append(foodName);
        if(foodGroupId != null){
            sb.append(FOOD_GROUP_REQ).append(foodGroupId);
        }
        sb.append(SORT_REQ).append(SORT_TYPE);
        sb.append(MAX_REQ).append(MAX_RESULTS);

        return sb.toString();
    }


    private String getNDBNoFromResponse(SearchAPIResponse response){

        Optional<SearchAPIResponse> optionalResponse = Optional.ofNullable(response);

        return optionalResponse
                .map(SearchAPIResponse::getResult)
                .flatMap(result -> result.getItems().stream().findFirst())
                .map(Item::getNdbNo)
                .orElse(null);
    }

}
