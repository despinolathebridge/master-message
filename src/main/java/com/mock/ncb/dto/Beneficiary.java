package com.mock.ncb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Beneficiary {

	private long beneficiaryId;
	private String bank;
	private String branch;
	private String accountType;
	private String accountNumber;
	private String name;
	private String lastName;
	private String currency;
	private String nickname;
	private boolean owner;

}
