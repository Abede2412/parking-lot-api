package tech.abede.parkinglotapi.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.parkinglotapi.models.dto.VehicleResponse;
import tech.abede.parkinglotapi.models.dto.VehicleResponseForUnpark;
import tech.abede.parkinglotapi.utils.Utils;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String nomorPolisi;

    @Enumerated(EnumType.STRING)
    private StatusPark status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @CreationTimestamp
    private LocalDateTime parkAt;

    @UpdateTimestamp
    private LocalDateTime unparkAt;

    public VehicleResponse convertToResponse(){

        String dateTimeIn = Utils.format(parkAt);

        return VehicleResponse.builder()
            .parkingId(id)
            .vehicleType(type.name())
            .nomorPolisi(nomorPolisi)
            .parkingSpot(spot.getId())
            .dateTime(dateTimeIn)
            .build();
    }

    public VehicleResponseForUnpark convertToResponseForUnpark() {

        String dateTimeOut = Utils.format(unparkAt);
        String dateTimeIn = Utils.format(parkAt);

        return VehicleResponseForUnpark.builder()
            .parkingId(id)
            .vehicleType(type.name())
            .nomorPolisi(nomorPolisi)
            .parkingSpot(spot.getId())
            .dateTimeIn(dateTimeIn)
            .dateTimeOut(dateTimeOut)
            .fee(getFee())
            .duration(getDuration())
            .build();
    }

    public Long getFee(){
        Duration duration = Duration.between(parkAt, unparkAt);
        Long diff = Double.valueOf(Math.ceil(duration.toMinutes()/60.0)).longValue();
        Long fee = diff*type.getChargePerhour();
        return fee;
    }

    public String getDuration(){
        Duration duration = Duration.between(parkAt, unparkAt);
        
        Long minutes = duration.toMinutes() % 60;
        Long hour = duration.toHours() % 24;
        Long day = duration.toDays();

        String durations = day + " day " + hour + " hour " + minutes + " minutes ";

        return durations;
    }
}
