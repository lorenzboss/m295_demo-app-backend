package ch.ilv.m295.demoapp.vehicleusage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleUsageRepository extends JpaRepository<VehicleUsage, Long> {
    List<VehicleUsage> findByOrderByFromDateDesc();
}
