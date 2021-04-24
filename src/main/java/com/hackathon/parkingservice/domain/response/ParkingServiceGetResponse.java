package com.hackathon.parkingservice.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingServiceGetResponse {
    private int free;
    private int taken;
    private int total;
}
