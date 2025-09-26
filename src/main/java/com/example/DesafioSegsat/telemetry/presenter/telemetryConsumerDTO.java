package com.example.DesafioSegsat.telemetry.presenter;

public record telemetryConsumerDTO(
        Double temperature,
        Double humidity,
        Integer sensorId,
        Double pressure

) {
}
