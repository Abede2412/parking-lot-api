package tech.abede.parkinglotapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    private Long parkingId;

    private String vehicleType;

    private String nomorPolisi;

    private Integer parkingSpot;

    private String dateTime;
    
}
