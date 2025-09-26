package com.example.DesafioSegsat.telemetry;

import com.example.DesafioSegsat.telemetry.presenter.res.TelemetryPaginationResDTO;
import com.example.DesafioSegsat.telemetry.presenter.res.TelemetryResDTO;
import com.example.DesafioSegsat.telemetry.presenter.telemetryConsumerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelemetryService {
    private final TelemetryRepository telemetryRepository;

    @KafkaListener(topics = "telemetry-data", groupId = "telemetry", containerFactory = "telemetryKafkaListenerFactory")
    public void consumeTelemetry(telemetryConsumerDTO dto) {
        try {
            createTelemetry(dto);
        } catch (Exception e){
            log.error("INVALID TELEMETRY LOG. ERROR MESSAGE: {}", e.getMessage());
        }
    }

    private void createTelemetry(telemetryConsumerDTO dto) {
        if(dto.temperature() < -273){
            throw new RuntimeException("Temperature is under 273 graus");
        }

        if(dto.humidity() < 0 || dto.humidity() > 100){
            throw new RuntimeException("Humidity is not between 0% to 100% ");
        }

        if(dto.sensorId() <= 0){
            throw new RuntimeException("Sensor id is under 0");
        }

        Telemetry telemetry = new Telemetry();
        BeanUtils.copyProperties(dto, telemetry);

        Telemetry savedTelemetry = telemetryRepository.save(telemetry);
        log.info("saved telemetry: {}", savedTelemetry);
    }

    public TelemetryResDTO findById(Long id) {
        Telemetry telemetry = telemetryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telemetry id not found"));

        return TelemetryResDTO.toDTO(telemetry);
    }

    public TelemetryPaginationResDTO findAllWithPagination(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Telemetry> telemetriesPage = telemetryRepository.findAllWithPagination(pageable);
        return new TelemetryPaginationResDTO(
                telemetriesPage.stream().map(TelemetryResDTO::toDTO).toList(),
                telemetriesPage.getTotalElements()
        );
    }
}
