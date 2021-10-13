package com.mock.ncb.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageTemplateEventDto {
    private String messageCode;
    private String messageVersion;
    private String language;
}
