package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MTopBalanceDebitedEvent {
    private Long transferId;
    private String userId;
    private String referenceId;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
}
