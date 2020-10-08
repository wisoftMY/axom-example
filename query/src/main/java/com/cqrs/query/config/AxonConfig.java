package com.cqrs.query.config;

import com.cqrs.query.version.HolderCreationEventV1;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.async.SequentialPerAggregatePolicy;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerTrackingEventProcessor(
                "accounts",
                org.axonframework.config.Configuration::eventStore,
                c -> TrackingEventProcessorConfiguration.forParallelProcessing(3)
                .andBatchSize(100)
        );

        configurer.registerSequencingPolicy("accounts",
                configuration -> SequentialPerAggregatePolicy.instance()); // 동일 Aggregate에 속한 Event는
                                                                           // 동일 Thread에서 처리 할 수 있게
    }

    @Bean
    public EventUpcasterChain eventUpcasterChain() {
        return new EventUpcasterChain(
                new HolderCreationEventV1()
        );
    }
}
