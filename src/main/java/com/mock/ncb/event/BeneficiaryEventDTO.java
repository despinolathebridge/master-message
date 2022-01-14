package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryEventDTO {
    private Long beneficiaryId;
    private String bank;
    private String branch;
    private String accountType;
    private String accountNumber;
    private String name;
    private String lastName;
    private String currency;
    private String nickname;
    private Boolean owner;
}
