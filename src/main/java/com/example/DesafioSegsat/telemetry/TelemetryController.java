package com.example.DesafioSegsat.telemetry;


import com.example.DesafioSegsat.telemetry.presenter.res.TelemetryPaginationResDTO;
import com.example.DesafioSegsat.telemetry.presenter.res.TelemetryResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/telemetry")
@RequiredArgsConstructor
public class TelemetryController {
    private  final TelemetryService telemetryService;

    @GetMapping("/{id}")
    public ResponseEntity<TelemetryResDTO> findById(@PathVariable Long id){
        TelemetryResDTO response = telemetryService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<TelemetryPaginationResDTO> findAllWithPagination(@PathVariable Integer page){
        TelemetryPaginationResDTO response = telemetryService.findAllWithPagination(page);
        return ResponseEntity.ok(response);
    }
}
