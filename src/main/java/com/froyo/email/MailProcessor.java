package com.froyo.email;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.froyo.email.response.MandrillResponseWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class MailProcessor {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static final String MANDRILL_URL = "https://mandrillapp.com/api/1.0/";

    private final String outputFormat;

    public MailProcessor(String outputFormat) {

        this.outputFormat = outputFormat;
    }

    public MandrillResponseWrapper sendEmail(String endpoint, String json) throws MandrillMessageException{

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost(MANDRILL_URL + endpoint + "." + outputFormat);

        CloseableHttpResponse httpResponse = null;
        MandrillResponseWrapper responseWrapper = null;
        try {

            // Set encoding, charset and the entity to send
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            post.setEntity(new StringEntity(json, Charset.forName("UTF-8")));

            LOG.info("Sending to " + post.getURI().toURL().toString());

            httpResponse = client.execute(post);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            LOG.info("Status Code: " + statusCode);

            String jsonRes = IOUtils.toString(httpResponse.getEntity().getContent(), Charset.forName("UTF-8"));

            responseWrapper = MessageUtils.buildResponse(jsonRes);

        } catch (JsonMappingException e) {
            throw new MandrillMessageException("Could not map JSON document response " + e.getMessage());
        } catch (IOException e) {
            throw new MandrillMessageException("IOException thrown " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(httpResponse);
        }

        return responseWrapper;
    }
}
