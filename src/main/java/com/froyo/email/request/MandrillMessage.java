package com.froyo.email.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MandrillMessage {

    @JsonProperty("text")
	private String text;
    @JsonProperty("html")
    private String html;
    @JsonProperty("subject")
	private String subject;
    @JsonProperty("from_email")
	private String from_email;
    @JsonProperty("from_name")
	private String fromName;
    @JsonProperty("subaccount")
	private String subaccount;
    @JsonProperty("to")
	private MandrillRecipient[] to;
    @JsonProperty("bcc_address")
	private String bcc_address;
    @JsonProperty("track_opens")
	private boolean trackOpens;
    @JsonProperty("track_clicks")
	private boolean trackClicks;
    @JsonProperty("auto_text")
	private boolean autoText;
    @JsonProperty("url_strip_qs")
	private boolean urlStripQs;
    @JsonProperty("preserve_receipients")
	private boolean preserveRecipients;
    @JsonProperty("tags")
	private String[] tags = new String[0];
    @JsonProperty("google_analytics_domains")
	private String[] googleAnalyticsDomains = new String[0];
    @JsonProperty("google_analytics_campaign")
	private String[] googleAnalyticsCampaign = new String[0];
    @JsonProperty("global_merge_vars")
    private List<MandrillMergeVar> globalMergeVars;
    @JsonProperty("merge_vars")
    private List<MessageMergeVars> mergeVars;
    @JsonProperty("attachments")
    private List<MandrillAttachment> attachments;
    @JsonProperty("important")
    private boolean important;
    @JsonProperty("auto_html")
    private boolean autoHtml;
    @JsonProperty("inline_css")
    private boolean inlineCss;
    @JsonProperty("merge")
    private boolean merge;
    @JsonProperty("merge_language")
    private String mergeLanguage;
    @JsonProperty("metadata")
    private Map<String,Object> metadata;
    @JsonProperty("receipient_metadata")
    private Map<String,Object> recipientMetadata;
    @JsonProperty("headers")
    private Map<String, String> headers;

    public List<MandrillRecipient> addTo(String email, String name) {
        List<MandrillRecipient> recipientsList = new ArrayList<MandrillRecipient>();
        recipientsList.add(new MandrillRecipient(email, name));
        return recipientsList;
    }

    public List<MandrillRecipient> addTo(String email, String name, String type) {
        List<MandrillRecipient> recipientsList = new ArrayList<MandrillRecipient>();
        recipientsList.add(new MandrillRecipient(name, email, type));
        return recipientsList;
    }
}
