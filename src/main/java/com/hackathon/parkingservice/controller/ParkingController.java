package com.hackathon.parkingservice.controller;

import com.hackathon.parkingservice.domain.request.ReservationRequest;
import com.hackathon.parkingservice.domain.response.ReservationInfo;
import com.hackathon.parkingservice.service.ParkingService;
import com.hackathon.parkingservice.domain.response.ParkingServiceGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping(value = "/reservation")
    public void reservation(@RequestBody final ReservationRequest request) {
        parkingService.reservation(request);
    }

    @GetMapping(value = "/getInfo")
    public ParkingServiceGetResponse getInformation() {
        return parkingService.getInfo();
    }


    @GetMapping(value = "/list")
    public List<ReservationInfo> list() {
        return parkingService.getAllReservation();
    }
}
