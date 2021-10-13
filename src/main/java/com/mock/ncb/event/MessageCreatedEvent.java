package com.mock.ncb.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCreatedEvent {
    private String userId;
    private String classification;
    private String priority;
    private String title;
    private String body;
    private String action;
    private Map<String, Object> data;
    private MessageTemplateEventDto messageTemplate;
    private List<String> tags;
    private String notificationType;
    private String notificationId;

    public MessageCreatedEvent() {
        this.userId = null;
        this.classification = null;
        this.priority = null;
        this.title = null;
        this.body = null;
        this.action = null;
        this.data = null;
        this.messageTemplate = null;
        this.tags = null;
        this.notificationType = null;
        this.notificationId = null;
    }

}
