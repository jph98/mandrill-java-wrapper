package com.froyo.email.request;

public class MandrillMergeVar {

    private String name;
    private Object content;
    public MandrillMergeVar(String name, Object content) {
        this.name = name;
        this.content = content;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
