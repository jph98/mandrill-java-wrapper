package com.froyo.email.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MandrillRecipient {

	private String email;
	private String name;
    private String type;

	public MandrillRecipient(String name, String email) {
		this.email = email;
		this.name = name;
	}
}
