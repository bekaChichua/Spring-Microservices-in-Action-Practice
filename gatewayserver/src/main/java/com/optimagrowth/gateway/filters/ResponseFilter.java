package com.optimagrowth.gateway.filters;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ResponseFilter {
    @Autowired
    FilterUtils filterUtils;
    @Autowired
    Tracer tracer;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String traceId = tracer.currentSpan().context().traceIdString();
            log.warn("Adding the correlation id to the outbound headers. {}", traceId);
            exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, traceId);
            log.warn("Completing outgoing request for {}.", exchange.getRequest().getURI());
        }));
    }
}
