package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BalanceTransferP2PEvent {
    private Long transferId;
    private Long sourceAccountId;
    private String sourceUserId;
    private Long amount;
    private String currency;
    private Long targetAccountId;
    private String targetUserId;
    private String comments;
    private String category;
}
