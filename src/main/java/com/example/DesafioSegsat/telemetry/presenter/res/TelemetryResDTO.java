package com.example.DesafioSegsat.telemetry.presenter.res;

import com.example.DesafioSegsat.telemetry.Telemetry;

import java.time.LocalDateTime;

public record TelemetryResDTO(
        Long id,
        Integer sensorId,
        Double temperature,
        Double humidity,
        Double pressure,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TelemetryResDTO toDTO(Telemetry telemetry) {
        return new TelemetryResDTO(
                telemetry.getId(),
                telemetry.getSensorId(),
                telemetry.getTemperature(),
                telemetry.getHumidity(),
                telemetry.getPressure(),
                telemetry.getCreatedAt(),
                telemetry.getUpdatedAt()
        );
    }
}
