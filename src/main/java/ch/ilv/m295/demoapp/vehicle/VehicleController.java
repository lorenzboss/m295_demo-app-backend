package ch.ilv.m295.demoapp.vehicle;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("api/vehicle")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Vehicle>> all() {
        List<Vehicle> result = vehicleService.getVehicles();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/vehicle/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Vehicle> one(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("api/vehicle")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Vehicle> newVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.insertVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.OK);
    }

    @PutMapping("api/vehicle/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable Long id) {
        Vehicle savedVehicle = vehicleService.updateVehicle(vehicle, id);
        return new ResponseEntity<>(savedVehicle, HttpStatus.OK);
    }

    @DeleteMapping("api/vehicle/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteVehicle(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehicleService.deleteVehicle(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
