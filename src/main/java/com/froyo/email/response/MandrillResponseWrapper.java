package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * MandrillResponseWrapper
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillResponseWrapper {

    private List<MandrillSentResponse> successMessages = new ArrayList<MandrillSentResponse>();
    private List<MandrillErrorResponse> errorMessages = new ArrayList<MandrillErrorResponse>();

    public List<MandrillSentResponse> getSuccessMessages() {
        return successMessages;
    }

    public void setSuccessMessages(List<MandrillSentResponse> successMessages) {
        this.successMessages = successMessages;
    }

    public List<MandrillErrorResponse> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<MandrillErrorResponse> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void addSuccessMessage(MandrillSentResponse s) {

        successMessages.add(s);
    }

    public void addErrorMessage(MandrillErrorResponse e) {

        errorMessages.add(e);
    }

    public double getSuccessRate() {
        return (successMessages.size() / successMessages.size() + errorMessages.size()) * 100;
    }

    public double getErrorRate() {
        return (errorMessages.size() / successMessages.size() + errorMessages.size()) * 100;
    }

    public int getTotalMessages() {
        return successMessages.size() + errorMessages.size();
    }
}
