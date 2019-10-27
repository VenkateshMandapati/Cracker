package com.codelove.cracker.ndbapi.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomErrorMapper extends JsonDeserializer<NDBAPIErrors> {


    @Override
    public NDBAPIErrors deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        
        NDBAPIErrors ndbAPIErrors = new NDBAPIErrors();
        
        if(node.get("errors") != null){
            final Integer httpStatus = node.get("errors").get("error").get(0).get("status").asInt();
            final String message = node.get("errors").get("error").get(0).get("message").asText();

            ndbAPIErrors = new NDBAPIErrors();
            ndbAPIErrors.setMessage(message);
            ndbAPIErrors.setHttpStatus(httpStatus);
        }

        return ndbAPIErrors;
    }
}
