package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillResponseWrapper {

    private List<MandrillSentResponse> successMessages = new ArrayList<MandrillSentResponse>();
    private List<MandrillErrorResponse> errorMessages = new ArrayList<MandrillErrorResponse>();

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
}
