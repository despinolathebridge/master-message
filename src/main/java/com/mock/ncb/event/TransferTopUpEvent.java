package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferTopUpEvent {

	private Long transferId;
	private String userId;
	private String bankIdentifier;
	private String accountReference;
	private Long accountId;
	private BigDecimal amount;
	private String currency;
}
