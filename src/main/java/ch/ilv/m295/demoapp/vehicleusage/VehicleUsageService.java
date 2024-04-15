package ch.ilv.m295.demoapp.vehicleusage;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleUsageService {

    private final VehicleUsageRepository repository;

    public VehicleUsageService(VehicleUsageRepository repository) {
        this.repository = repository;
    }

    public List<VehicleUsage> getVehicleUsages() {
        return repository.findByOrderByFromDateDesc();
    }

    public VehicleUsage getVehicleUsage(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, VehicleUsage.class));
    }

    public VehicleUsage insertVehicleUsage(VehicleUsage usage) {
        return repository.save(usage);
    }

    public VehicleUsage updateVehicleUsage(VehicleUsage usage, Long id) {
        return repository.findById(id)
                .map(usageOrig -> {
                    usageOrig.setFromDate(usage.getFromDate());
                    usageOrig.setToDate(usage.getToDate());
                    usageOrig.setFromLocation(usage.getFromLocation());
                    usageOrig.setToLocation(usage.getToLocation());
                    usageOrig.setKm(usage.getKm());
                    usageOrig.setText(usage.getText());
                    usageOrig.setEmployee(usage.getEmployee());
                    usageOrig.setVehicle(usage.getVehicle());
                    return repository.save(usageOrig);
                })
                .orElseGet(() ->
                        repository.save(usage));
    }

    public MessageResponse deleteVehicleUsage(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Vehicle Usage " + id + " deleted");
    }
}
