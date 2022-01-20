package com.optimagrowth.license.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(Sink.class)
public class OrganizationChangeHandler {
    @StreamListener(Sink.INPUT)
    public void loggerSink(OrganizationChangeModel organization) {
        log.warn("Received a message of type " + organization.getType());
        switch (organization.getAction()) {
            case "GET":
                log.warn("Received a GET event from the organization service for organization id {}", organization.getOrganizationId());
                break;
            case "CREATED":
                log.warn("Received a SAVE event from the organization service for organization id {}", organization.getOrganizationId());
                break;
            case "UPDATE":
                log.warn("Received a UPDATE event from the organization service for organization id {}", organization.getOrganizationId());
                break;
            case "DELETE":
                log.warn("Received a DELETE event from the organization service for organization id {}", organization.getOrganizationId());
                break;
            default:
                log.error("Received an UNKNOWN event from the organization service of type {}", organization.getType());
                break;
        }
    }
}
