package com.froyo.email.response;

public class MandrillReturnMessage {

    private String response;
    private boolean success;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    };
}
