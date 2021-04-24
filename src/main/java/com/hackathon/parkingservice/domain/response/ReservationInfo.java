package com.hackathon.parkingservice.domain.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationInfo {
    private final String id;
    private final String timeStamp;
}
