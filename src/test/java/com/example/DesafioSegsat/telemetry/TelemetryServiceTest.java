package com.example.DesafioSegsat.telemetry;

import com.example.DesafioSegsat.telemetry.presenter.telemetryConsumerDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TelemetryServiceTest {

    @Mock
    private TelemetryRepository telemetryRepository;

    @InjectMocks
    private TelemetryService telemetryService;

    @Test
    void testConsumeTelemetry_savesTelemetry() {
        double temperature = 12.5;
        double humidity = 10.1;
        double pressure = 4;
        int sensorId = 1;

        telemetryConsumerDTO dto = new telemetryConsumerDTO(
                temperature,
                humidity,
                sensorId,
                pressure
        );

        Telemetry expectedTelemetry = new Telemetry();
        BeanUtils.copyProperties(dto, expectedTelemetry);

        telemetryService.consumeTelemetry(dto);

        ArgumentCaptor<Telemetry> captor = ArgumentCaptor.forClass(Telemetry.class);
        verify(telemetryRepository, times(1)).save(captor.capture());

        Telemetry currentTelemetry = captor.getValue();
        assertEquals(expectedTelemetry, currentTelemetry);
    }

    @Test
    void testConsumeTelemetry_notSaveBecauseTemperatureIsUnder273() {
        double temperature = -274;
        double humidity = 10.1;
        double pressure = 4;
        int sensorId = 1;

        telemetryConsumerDTO dto = new telemetryConsumerDTO(
                temperature,
                humidity,
                sensorId,
                pressure
        );

        telemetryService.consumeTelemetry(dto);

        verify(telemetryRepository, times(0)).save(any(Telemetry.class));
    }

    @Test
    void testConsumeTelemetry_notSaveBecauseHumidityIsUpper100() {
        double temperature = 22.5;
        double humidity = 100.1;
        double pressure = 4;
        int sensorId = 1;

        telemetryConsumerDTO dto = new telemetryConsumerDTO(
                temperature,
                humidity,
                sensorId,
                pressure
        );

        telemetryService.consumeTelemetry(dto);

        verify(telemetryRepository, times(0)).save(any(Telemetry.class));
    }

    @Test
    void testConsumeTelemetry_notSaveBecauseHumidityIsUnder0() {
        double temperature = 22.5;
        double humidity = -1;
        double pressure = 4;
        int sensorId = 1;

        telemetryConsumerDTO dto = new telemetryConsumerDTO(
                temperature,
                humidity,
                sensorId,
                pressure
        );

        telemetryService.consumeTelemetry(dto);

        verify(telemetryRepository, times(0)).save(any(Telemetry.class));
    }

    @Test
    void testConsumeTelemetry_notSaveBecauseSensorIdIsUnder0() {
        double temperature = 22.5;
        double humidity = 12;
        double pressure = 4;
        int sensorId = 0;

        telemetryConsumerDTO dto = new telemetryConsumerDTO(
                temperature,
                humidity,
                sensorId,
                pressure
        );

        telemetryService.consumeTelemetry(dto);

        verify(telemetryRepository, times(0)).save(any(Telemetry.class));
    }
}