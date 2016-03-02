package com.froyo.email;

import com.froyo.email.request.MandrillMessage;
import com.froyo.email.request.MandrillRecipient;
import com.froyo.email.request.MandrillRequestWrapper;
import com.froyo.email.response.MandrillErrorResponse;
import com.froyo.email.response.MandrillResponseWrapper;
import com.froyo.email.response.MandrillSentResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestMailProcessor {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private String key;

    @Before
    public void before() throws IOException {

        // TODO: Move into a environment variable
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("mandrill.properties"));
        key = (String) props.get("key");
    }

    @Test
    public void shouldSendEmail() throws MandrillMessageException {

        MailProcessor processor = new MailProcessor("json");

        MandrillMessage message = buildMessage();
        MandrillRequestWrapper request = new MandrillRequestWrapper(key, message);
        String json = MessageUtils.buildMessage(request, true);
        LOG.info("JSON Request: " + json);

        MandrillResponseWrapper responseWrapper = null;

        responseWrapper = processor.sendEmail("messages/send", json);
        displayStats(responseWrapper);
    }

    private MandrillMessage buildMessage() {

        MandrillMessage message = new MandrillMessage();

        List<MandrillRecipient> recips = message.addTo("mr.maloney@booboo.com", "Chester Maloney", "to");

        MandrillRecipient[] recipArray = recips.toArray(new MandrillRecipient[recips.size()]);
        message.setTo(recipArray);

        message.setText("Hello Jon TEXT");
        message.setHtml("Hello <b>Jon</b> HTML");
        message.setSubject("Goo Goo");
        message.setFrom_email("goo.goo@froyo.com");
        message.setFromName("Goo Goo");
        message.setImportant(false);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Reply-To", "goo.goo@froyo.com");

        message.setHeaders(headers);

        return message;
    }

    private void displayStats(MandrillResponseWrapper responseWrapper) {

        for (MandrillSentResponse success: responseWrapper.getSuccessMessages()) {
            LOG.info("Sent: " + success);
        }

        for (MandrillErrorResponse error: responseWrapper.getErrorMessages()) {
            LOG.info("Error: " + error);
        }

        //LOG.info(String.format("Success Rate: %.2f", responseWrapper.getSuccessRate()));
        //LOG.info(String.format("Error Rate: %.2f", responseWrapper.getErrorRate()));
    }
}
