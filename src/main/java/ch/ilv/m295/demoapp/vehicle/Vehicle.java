package ch.ilv.m295.demoapp.vehicle;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20, unique = true, nullable = false)
    @Size(max = 20)
    @NotEmpty
    private String licence;
    @Column()
    @Size(max = 255)
    private String description;
    @Column(nullable = false)
    @NotNull
    private VehicleType vehicleType;

    public Vehicle() {
    }

    public Vehicle(String licence, String description, VehicleType vehicleType) {
        this.licence = licence;
        this.description = description;
        this.vehicleType = vehicleType;
    }
}
