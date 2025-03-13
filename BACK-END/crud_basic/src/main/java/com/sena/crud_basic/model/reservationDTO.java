package com.sena.crud_basic.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class reservationDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private userDTO user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", nullable = false)
    private courtDTO court;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    
    // Constructor vacío requerido por JPA
    public reservationDTO() {
    }
    
    // Constructor con campos principales
    public reservationDTO(userDTO user, courtDTO court, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.user = user;
        this.court = court;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public userDTO getUsuario() {
        return user;
    }

    public void setUser(userDTO user) {
        this.user = user;
    }

    public courtDTO getCourt() {
        return court;
    }

    public void setCourt(courtDTO court) {
        this.court = court;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        reservationDTO other = (reservationDTO) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
    
    // Método para verificar si una reserva se solapa con otra
    public boolean seSolapaCon(reservationDTO otra) {
        if (!this.date.equals(otra.getDate()) || !this.court.equals(otra.getCourt())) {
            return false;
        }
        
        return !(this.endTime.isBefore(otra.getStartTime()) || this.startTime.isAfter(otra.getEndTime()));
    }
}