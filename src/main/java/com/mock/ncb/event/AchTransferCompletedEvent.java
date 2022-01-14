package com.mock.ncb.event;

import com.mock.ncb.dto.Beneficiary;
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
public class AchTransferCompletedEvent {

    private Long transferId;
    private Long accountId;
    private String userId;
    private Double amount;
    private String currency;
    private String achTransferReferenceId;
    private Beneficiary beneficiary;
}
