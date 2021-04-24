package com.hackathon.parkingservice.repository;

import com.hackathon.parkingservice.domain.entity.PersonReservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends CrudRepository<PersonReservation, String> {
}
