package tech.abede.parkinglotapi;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import tech.abede.parkinglotapi.models.Spot;
import tech.abede.parkinglotapi.repositories.SpotRepository;


@Component
@RequiredArgsConstructor
public class InitializingSpot {

    private final SpotRepository spotRepository;

    @PostConstruct
    private void postConstruct(){
        for (int i = 0; i < 10; i++){
            Spot spot = new Spot();
            spotRepository.save(spot);
        }
    }
}
