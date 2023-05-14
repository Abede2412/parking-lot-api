package tech.abede.parkinglotapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.abede.parkinglotapi.models.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer>{

    @Query(value = "select * from spot where status = 'AVAILABLE'", nativeQuery = true)
    List<Spot> findAllAvailableSpot(); 
}
