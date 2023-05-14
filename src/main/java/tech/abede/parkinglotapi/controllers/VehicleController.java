package tech.abede.parkinglotapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import tech.abede.parkinglotapi.models.Vehicle;
import tech.abede.parkinglotapi.models.dto.VehicleRequest;
import tech.abede.parkinglotapi.models.dto.VehicleResponse;
import tech.abede.parkinglotapi.models.dto.VehicleResponseForUnpark;
import tech.abede.parkinglotapi.services.VehicleService;

@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleResponse> park(@RequestBody VehicleRequest vehicleRequest){
        Vehicle vehicle = vehicleRequest.convertToEntity();
        Vehicle newVehicle = this.vehicleService.park(vehicle);
        VehicleResponse vehicleResponse = newVehicle.convertToResponse();
        return ResponseEntity.status(201).body(vehicleResponse);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleResponseForUnpark> unpark(@PathVariable("id") Long id){
        Vehicle vehicle = this.vehicleService.unPark(id);
        VehicleResponseForUnpark vehicleResponse = vehicle.convertToResponseForUnpark();
        return ResponseEntity.ok().body(vehicleResponse);
    }
}
