package com.mock.ncb.event;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TransferApprovedEvent {

	private Long transferId;
	private String provider;
	private String providerTxId;
	private Map<String, Object> details;

}
