package com.froyo.email.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MandrillMergeVar {

    private String name;
    private Object content;
}
