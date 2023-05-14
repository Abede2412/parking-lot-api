package tech.abede.parkinglotapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.parkinglotapi.models.Vehicle;
import tech.abede.parkinglotapi.models.VehicleType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    private VehicleType type;

    private String nomorPolisi;

    public Vehicle convertToEntity(){
        //VehicleType vehicleType = VehicleType.valueOf(type);
        return Vehicle.builder().type(type).nomorPolisi(nomorPolisi).build();
    }
    
}
