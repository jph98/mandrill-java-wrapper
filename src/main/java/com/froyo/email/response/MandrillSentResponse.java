package com.froyo.email.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MandrillSentResponse
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandrillSentResponse {

    private String id;
    private String email;
    private String status;
    private String rejectReason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return "MandrillSentResponse: " +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", rejectReason='" + rejectReason + "\'";
    }


}
