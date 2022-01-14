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
public class BalanceNCBCashInEvent {

  private long transferId;
  private long accountId;
  private String userId;
  private double amount;
  private String currency;
  private String bankReferenceId;
  private String finacleId;
  private String comments;

}
