package ch.ilv.m295.demoapp.vehicle;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> getVehicles() {
        return repository.findByOrderByLicenceAsc();
    }

    public Vehicle getVehicle(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Vehicle.class));
    }

    public Vehicle insertVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle, Long id) {
        return repository.findById(id)
                .map(vehicleOrig -> {
                    vehicleOrig.setVehicleType(vehicle.getVehicleType());
                    vehicleOrig.setDescription(vehicle.getDescription());
                    vehicleOrig.setLicence(vehicle.getLicence());
                    return repository.save(vehicleOrig);
                })
                .orElseGet(() -> repository.save(vehicle));
    }

    public MessageResponse deleteVehicle(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Vehicle " + id + " deleted");
    }
}
