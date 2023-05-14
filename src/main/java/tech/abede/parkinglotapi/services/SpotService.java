package tech.abede.parkinglotapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.abede.parkinglotapi.models.Spot;
import tech.abede.parkinglotapi.repositories.SpotRepository;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public List<Spot> getAvailableSpot(){
        return this.spotRepository.findAllAvailableSpot();
    }


    
}
