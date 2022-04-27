package com.mock.ncb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
	@JsonProperty("error_description")
	private String errorDescription;
	@JsonProperty("error_code")
	private String errorCode;
	private Date timestamp;
}
