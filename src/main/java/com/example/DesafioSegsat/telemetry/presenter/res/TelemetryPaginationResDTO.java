package com.example.DesafioSegsat.telemetry.presenter.res;

import java.util.List;

public record TelemetryPaginationResDTO(
        List<TelemetryResDTO> telemetries,
        Long totalItems
) {
}
