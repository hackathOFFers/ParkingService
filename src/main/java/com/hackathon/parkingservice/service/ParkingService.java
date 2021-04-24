package com.hackathon.parkingservice.service;

import com.hackathon.parkingservice.domain.entity.PersonReservation;
import com.hackathon.parkingservice.domain.request.ReservationRequest;
import com.hackathon.parkingservice.domain.response.ReservationInfo;
import com.hackathon.parkingservice.domain.response.ParkingServiceGetResponse;
import com.hackathon.parkingservice.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ParkingService {

    @Value("${url}")
    private String url;

    private final ParkingRepository parkingRepository;

    public ParkingServiceGetResponse getInfo() {
        final RestTemplate restTemplate = new RestTemplate();
        final ParkingServiceGetResponse body = restTemplate.getForEntity(url, ParkingServiceGetResponse.class).getBody();
        return getParkingServiceGetResponse(body);
    }

    private ParkingServiceGetResponse getParkingServiceGetResponse(final ParkingServiceGetResponse body) {
        final ParkingServiceGetResponse parkingServiceGetResponse = new ParkingServiceGetResponse();

        final int count = (int) parkingRepository.count();

        final int free = body.getFree();

        if (free == count) {
            parkingServiceGetResponse.setFree(0);
            parkingServiceGetResponse.setTaken(body.getTotal());
        } else if (free > count) {
            parkingServiceGetResponse.setFree(free - count);
            parkingServiceGetResponse.setTaken(body.getTaken() + count);
        }
        parkingServiceGetResponse.setTotal(body.getTotal());
        return parkingServiceGetResponse;
    }

    public void reservation(final ReservationRequest request) {
        final PersonReservation s = new PersonReservation();

        s.setTime(LocalTime.now().toString());
        s.setUserId(request.getId());

        parkingRepository.save(s);
    }

    public List<ReservationInfo> getAllReservation() {
        return StreamSupport.stream(parkingRepository.findAll().spliterator(), false)
                .map(x -> new ReservationInfo(x.getUserId(), x.getTime().substring(0, 5)))
                .collect(Collectors.toList());
    }
}
