package com.froyo.email;

import com.froyo.email.request.MandrillMessage;
import com.froyo.email.request.MandrillRecipient;
import com.froyo.email.request.MandrillRequestWrapper;
import com.froyo.email.response.MandrillErrorResponse;
import com.froyo.email.response.MandrillResponseWrapper;
import com.froyo.email.response.MandrillSentResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestMailProcessor {

    @Test
    public void testSendEmail() throws IOException {

        MailProcessor processor = new MailProcessor("json");

        // TODO: Move into a environment variable
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("mandrill.properties"));
        String key = (String) props.get("key");

        MandrillMessage message = buildMessage();
        MandrillRequestWrapper request = new MandrillRequestWrapper(key, message);
        String json = MessageUtils.buildMessage(request, true);
        System.out.println("JSON Request: " + json);

        MandrillResponseWrapper responseWrapper = processor.sendEmail("messages/send", json);

        displayStats(responseWrapper);
    }

    private MandrillMessage buildMessage() {

        MandrillMessage message = new MandrillMessage();

        List<MandrillRecipient> recips = message.addTo("mr.maloney@booboo.com", "Chester Maloney", "to");
        message.setTo(recips);

        message.setText("Hello Jon TEXT");
        message.setHtml("Hello <b>Jon</b> HTML");
        message.setSubject("Goo Goo");
        message.setFrom_email("goo.goo@froyo.com");
        message.setFrom_name("Goo Goo");
        message.setImportant(false);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Reply-To", "goo.goo@froyo.com");

        message.setHeaders(headers);
        return message;
    }

    private void displayStats(MandrillResponseWrapper responseWrapper) {
        for (MandrillSentResponse success: responseWrapper.getSuccessMessages()) {
            System.out.println("Sent: " + success);
        }

        for (MandrillErrorResponse error: responseWrapper.getErrorMessages()) {
            System.out.println("Error: " + error);
        }

        System.out.println(String.format("Success Rate: %.2f", responseWrapper.getSuccessRate()));
        System.out.println(String.format("Error Rate: %.2f", responseWrapper.getErrorRate()));
    }
}
