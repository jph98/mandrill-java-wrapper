package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillSentResponse {

    private String id;
    private String email;
    private String status;
    private String rejectReason;
}
