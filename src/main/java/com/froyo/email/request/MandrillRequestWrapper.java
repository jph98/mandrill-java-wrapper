package com.froyo.email.request;

public class MandrillRequestWrapper {

    private String key;
    private MandrillMessage message;
    private boolean async;
    private String ip_pool;
    private String sent_at;

    // TODO: view_content_link
    // TODO: tracking_domain
    // TODO: signing_domain
    // TODO: return_path_domain
    // TODO: async
    // TODO: ip_pool
    // TODO: send_at

    public MandrillRequestWrapper(String key, MandrillMessage message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MandrillMessage getMessage() {
        return message;
    }

    public void setMessage(MandrillMessage message) {
        this.message = message;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getIp_pool() {
        return ip_pool;
    }

    public void setIp_pool(String ip_pool) {
        this.ip_pool = ip_pool;
    }

    public String getSent_at() {
        return sent_at;
    }

    public void setSent_at(String sent_at) {
        this.sent_at = sent_at;
    }
}
