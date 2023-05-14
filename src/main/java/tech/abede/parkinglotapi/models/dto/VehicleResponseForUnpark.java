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
public class VehicleResponseForUnpark {

    private Long parkingId;

    private String vehicleType;

    private String nomorPolisi;

    private Integer parkingSpot;

    private String dateTimeIn;

    private String dateTimeOut;

    private String duration;

    private Long fee;
}
