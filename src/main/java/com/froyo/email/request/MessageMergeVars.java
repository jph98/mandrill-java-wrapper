package com.froyo.email.request;

import java.util.List;

public class MessageMergeVars {
    private String rcpt;
    private List<MandrillMergeVar> vars;

    public String getRcpt() {
        return rcpt;
    }

    public void setRcpt(String rcpt) {
        this.rcpt = rcpt;
    }

    public List<MandrillMergeVar> getVars() {
        return vars;
    }

    public void setVars(List<MandrillMergeVar> vars) {
        this.vars = vars;
    }
}
