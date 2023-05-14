package tech.abede.parkinglotapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.abede.parkinglotapi.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle getVehicleIsParkingById(Long id);
    
}
