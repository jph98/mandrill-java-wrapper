package com.froyo.email.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MandrillAttachment {

    private String type;
    private String name;
    private String content;
}
