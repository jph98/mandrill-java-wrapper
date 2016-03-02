package com.froyo.email.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MandrillRequestWrapper {

    @JsonProperty("key")
    private String key;
    @JsonProperty("message")
    private MandrillMessage message;
    @JsonProperty("async")
    private boolean async;
    @JsonProperty("ip_pool")
    private String ipPool;
    @JsonProperty("sent_at")
    private String sentAt;

    // TODO: view_content_link
    @JsonProperty("view_content_link")
    private String viewContentLink;
    @JsonProperty("tracking_domain")
    private String trackingDomain;
    @JsonProperty("signing_domain")
    private String signingDomain;
    @JsonProperty("return_path_domain")
    private String returnPathDomain;

    public MandrillRequestWrapper(String key, MandrillMessage message) {

        this.key = key;
        this.message = message;
    }
}
