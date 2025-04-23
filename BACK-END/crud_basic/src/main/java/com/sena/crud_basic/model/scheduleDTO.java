package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "schedule")
public class scheduleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchedule")
    private Long idSchedule;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCourt")
    private Long idCourt;

    @Column(name = "weekDay", nullable = false, length = 10)
    private String weekDay;

    @Column(name = "openingTime")
    private LocalDateTime openingTime;

    @Column(name = "closingTime")
    private LocalDateTime closingTime;

    @Column(name = "available")
    private boolean available;


    // Getters and Setters

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Long getIdCourt() {
        return idCourt;
    }

    public void setIdCourt(Long idCourt) {
        this.idCourt = idCourt;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setType(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
