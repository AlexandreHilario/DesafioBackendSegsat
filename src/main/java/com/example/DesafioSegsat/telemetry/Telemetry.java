package com.example.DesafioSegsat.telemetry;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_telemetry")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telemetry_id")
    private Long id;

    @NotNull
    private Integer sensorId;

    @NotNull
    private  Double temperature;

    @NotNull
    private  Double humidity;

    @NotNull
    private  Double pressure;

    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdAt;

    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Telemetry telemetry = (Telemetry) o;
        return Objects.equals(id, telemetry.id) && Objects.equals(sensorId, telemetry.sensorId) && Objects.equals(temperature, telemetry.temperature) && Objects.equals(humidity, telemetry.humidity) && Objects.equals(pressure, telemetry.pressure) && Objects.equals(createdAt, telemetry.createdAt) && Objects.equals(updatedAt, telemetry.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sensorId, temperature, humidity, pressure, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Telemetry{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
