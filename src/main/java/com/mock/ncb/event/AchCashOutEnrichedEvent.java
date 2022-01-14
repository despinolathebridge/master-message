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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchCashOutEnrichedEvent {
    private Long transferId;
    private Long accountId;
    private String userId;
    private Double amount;
    private String currency;
    private String comments;
    private BeneficiaryEventDTO beneficiary;
}
