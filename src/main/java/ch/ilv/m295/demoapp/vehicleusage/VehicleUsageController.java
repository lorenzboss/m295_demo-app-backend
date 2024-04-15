package ch.ilv.m295.demoapp.vehicleusage;

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
public class VehicleUsageController {

    private final VehicleUsageService vehicleUsageService;

    VehicleUsageController(VehicleUsageService vehicleUsageService) {
        this.vehicleUsageService = vehicleUsageService;
    }

    @GetMapping("api/vehicleusage")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<VehicleUsage>> all() {
        List<VehicleUsage> result = vehicleUsageService.getVehicleUsages();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/vehicleusage/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<VehicleUsage> one(@PathVariable Long id) {
        VehicleUsage usage = vehicleUsageService.getVehicleUsage(id);
        return new ResponseEntity<>(usage, HttpStatus.OK);
    }

    @PostMapping("api/vehicleusage")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<VehicleUsage> newVehicleUsage(@Valid @RequestBody VehicleUsage usage) {
        VehicleUsage savedUsage = vehicleUsageService.insertVehicleUsage(usage);
        return new ResponseEntity<>(savedUsage, HttpStatus.OK);
    }

    @PutMapping("api/vehicleusage/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<VehicleUsage> updateVehicleUsage(@Valid @RequestBody VehicleUsage usage, @PathVariable Long id) {
        VehicleUsage savedUsage = vehicleUsageService.updateVehicleUsage(usage, id);
        return new ResponseEntity<>(savedUsage, HttpStatus.OK);
    }

    @DeleteMapping("api/vehicleusage/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<MessageResponse> deleteVehicleUsage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehicleUsageService.deleteVehicleUsage(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
