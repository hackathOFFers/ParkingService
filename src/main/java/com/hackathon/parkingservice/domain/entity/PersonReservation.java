package com.hackathon.parkingservice.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personReservation")
@Getter
@Setter
public class PersonReservation {
    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "time")
    private String time;
}
