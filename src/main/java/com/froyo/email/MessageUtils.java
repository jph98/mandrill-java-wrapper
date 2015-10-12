package com.froyo.email;

import com.froyo.email.request.MandrillRequestWrapper;
import com.froyo.email.response.MandrillErrorResponse;
import com.froyo.email.response.MandrillResponseWrapper;
import com.froyo.email.response.MandrillSentResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MessageUtils {

    public static String buildMessage(MandrillRequestWrapper request, boolean prettyPrint) {

        String output = null;
        ObjectMapper mapper = new ObjectMapper();

        try {

            if (prettyPrint) {
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                output = writer.writeValueAsString(request);
            } else {
                output = mapper.writeValueAsString(request);
            }

        } catch (JsonProcessingException e) {
            System.out.println("Json processing exception " + e);
        }

        return output;
    }

    public static MandrillResponseWrapper buildResponse(String json) {

        ObjectMapper mapper = new ObjectMapper();

        MandrillResponseWrapper wrapper = new MandrillResponseWrapper();
        try {

            // Mixed error and success messages so serialize to String array
            List<Object> myObjects = Arrays.asList(mapper.readValue(json, Object[].class));

            for (Object obj: myObjects) {
                Map<String, Object> p = mapper.convertValue(obj, Map.class);

                if (p.get("status").equals("error")) {

                    MandrillErrorResponse error = new MandrillErrorResponse();
                    error.setCode((String) p.get("code"));
                    error.setName((String) p.get("name"));
                    error.setMessage((String) p.get("message"));
                    error.setStatus((String) p.get("status"));
                    wrapper.addErrorMessage(error);

                } else {
                    MandrillSentResponse success = new MandrillSentResponse();
                    success.setEmail((String) p.get("email"));
                    success.setStatus((String) p.get("status"));
                    success.setRejectReason((String) p.get("reject_reason"));
                    success.setId((String) p.get("_id"));
                    wrapper.addSuccessMessage(success);
                }
            }

            return wrapper;

        } catch (JsonMappingException e) {
            System.out.println("Json Mapping Exception " + e);
        } catch (JsonParseException e) {
            System.out.println("Json Parse Exception " + e);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }

        return wrapper;
    }

}