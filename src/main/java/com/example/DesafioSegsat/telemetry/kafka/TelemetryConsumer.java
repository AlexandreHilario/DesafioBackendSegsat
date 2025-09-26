package com.example.DesafioSegsat.telemetry.kafka;

import com.example.DesafioSegsat.kafka.KafkaConfig;
import com.example.DesafioSegsat.telemetry.presenter.telemetryConsumerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@RequiredArgsConstructor
public class TelemetryConsumer {
    private final KafkaConfig kafkaConfig;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, telemetryConsumerDTO> telemetryKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, telemetryConsumerDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConfig.consumerFactory(telemetryConsumerDTO.class, "telemetry"));
        return factory;
    }
}
