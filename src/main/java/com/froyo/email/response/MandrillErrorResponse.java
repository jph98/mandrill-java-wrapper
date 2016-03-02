package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillErrorResponse {

    private String status;
    private String code;
    private String name;
    private String message;
}

