package com.mock.ncb.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class UserCreatedEvent {
    private final String userId;
    private final String phoneNumber;
    private final String status;
}
