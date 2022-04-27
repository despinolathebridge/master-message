package com.mock.ncb.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbmCashOutTransfersEvent {
	private Long transferId;
	private String userId;
	private String bankIdentifier;
	private String referenceId;
	private Long accountId;
	private Double amount;
	private String currency;
}
