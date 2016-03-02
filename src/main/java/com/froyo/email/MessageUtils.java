package com.froyo.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.froyo.email.request.MandrillRequestWrapper;
import com.froyo.email.response.MandrillErrorResponse;
import com.froyo.email.response.MandrillResponseWrapper;
import com.froyo.email.response.MandrillSentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MessageUtils {

    private static final Logger LOG = LoggerFactory.getLogger(MessageUtils.class);

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
            LOG.error("Json processing exception " + e);
        }

        return output;
    }

    public static MandrillResponseWrapper buildResponse(String json) throws IOException {

        MandrillResponseWrapper wrapper = new MandrillResponseWrapper();

        // Mixed error and success messages so serialize to String array
        ObjectMapper mapper = new ObjectMapper();
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
    }

}