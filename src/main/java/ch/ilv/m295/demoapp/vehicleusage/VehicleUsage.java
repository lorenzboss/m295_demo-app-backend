package ch.ilv.m295.demoapp.vehicleusage;

import ch.ilv.m295.demoapp.employee.Employee;
import ch.ilv.m295.demoapp.vehicle.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class VehicleUsage {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fromDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date toDate;
    @Column(nullable = false, length = 100)
    @Size(max = 100)
    @NotEmpty
    private String fromLocation;
    @Column(nullable = false, length = 100)
    @Size(max = 100)
    @NotEmpty
    private String toLocation;
    @Column(nullable = false, length = 500)
    @Size(max = 500)
    private String text;
    @Column(nullable = false, precision = 9, scale = 2)
    @NotNull
    private BigDecimal km;
    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    @NotNull
    private Vehicle vehicle;
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    public VehicleUsage() {
    }
}
