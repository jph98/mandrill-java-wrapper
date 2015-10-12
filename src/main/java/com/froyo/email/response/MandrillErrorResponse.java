package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MandrillErrorResponse
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillErrorResponse {

    private String status;
    private String code;
    private String name;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {

        return "MandrillErrorResponse: " +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + "\'";
    }
}

