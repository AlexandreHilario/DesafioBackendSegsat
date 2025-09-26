package com.example.DesafioSegsat.telemetry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelemetryRepository extends JpaRepository <Telemetry,Long> {
    @Query("""
            SELECT t FROM Telemetry t
            """)
    Page<Telemetry> findAllWithPagination(Pageable pageable);
}
