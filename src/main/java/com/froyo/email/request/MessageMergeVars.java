package com.froyo.email.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageMergeVars {

    private String rcpt;
    private List<MandrillMergeVar> vars;
}
