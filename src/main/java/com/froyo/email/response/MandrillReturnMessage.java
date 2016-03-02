package com.froyo.email.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MandrillReturnMessage {

    private String response;
    private boolean success;
}
