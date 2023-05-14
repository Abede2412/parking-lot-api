package tech.abede.parkinglotapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.parkinglotapi.exceptions.SpotNotAvailableException;
import tech.abede.parkinglotapi.exceptions.VehicleNotFoundException;
import tech.abede.parkinglotapi.models.Spot;
import tech.abede.parkinglotapi.models.Status;
import tech.abede.parkinglotapi.models.StatusPark;
import tech.abede.parkinglotapi.models.Vehicle;
import tech.abede.parkinglotapi.repositories.VehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final SpotService spotService;

    public Vehicle park(Vehicle vehicle){
        List<Spot> availableSpots = this.spotService.getAvailableSpot();
        if (availableSpots.size() == 0){
            throw new SpotNotAvailableException("Parking space not available");
        }
        Spot spot = availableSpots.get(0);
        spot.setStatus(Status.UNAVAILABLE);
        vehicle.setSpot(spot);
        vehicle.setStatus(StatusPark.PARK);
        return this.vehicleRepository.save(vehicle);
    } 

    public Vehicle unPark(Long id){
        Vehicle existingVehicle = this.getById(id);
        existingVehicle.setStatus(StatusPark.UNPARK);
        Spot spot = existingVehicle.getSpot();
        spot.setStatus(Status.AVAILABLE);
        return this.vehicleRepository.save(existingVehicle);
    }

    public Vehicle getById(Long id) {
        Vehicle vehicle = this.vehicleRepository.getVehicleIsParkingById(id);
        if (vehicle == null){
            throw new VehicleNotFoundException("Vehicle not found");
        }
        return vehicle;
    }
}
