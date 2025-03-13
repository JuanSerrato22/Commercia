package com.sena.crud_basic.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "court")
public class courtDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String type;
    
    @Column(columnDefinition = "TEXT")
    private String description;
            
    @Column(nullable = false)
    private Boolean available;
    
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<reservationDTO> reservation = new HashSet<>();
    
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<scheduleDTO> schedule = new HashSet<>();
    
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<usercourtDTO> usercourt = new HashSet<>();
    
    // Constructor vacío requerido por JPA
    public courtDTO() {
    }
    
    // Constructor con campos principales
    public courtDTO(String name, String type, Boolean available) {
        this.name = name;
        this.type = type;
        this.available = available;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<reservationDTO> getReservation() {
        return reservation;
    }

    public void setReservation(Set<reservationDTO> reservation) {
        this.reservation = reservation;
    }

    public Set<scheduleDTO> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<scheduleDTO> schedule) {
        this.schedule = schedule;
    }

    public Set<usercourtDTO> getUserCourt() {
        return usercourt;
    }

    public void setUserCourt(Set<usercourtDTO> usercourt) {
        this.usercourt = usercourt;
    }
    
    // Métodos helper para gestionar relaciones bidireccionales
    public void addHorario(scheduleDTO horario) {
        this.schedule.add((scheduleDTO) schedule);
        ((reservationDTO) schedule).setCourt(this);
    }
    
    public void removeHorario(scheduleDTO horario) {
        this.schedule.remove(schedule);
        schedule.setCourt(null);
    }
    
    public void addReserva(reservationDTO reserva) {
        this.reservation.add(reservation);
        reservation.setCourt(this);
    }
    
    public void removeReserva(reservationDTO reserva) {
        this.reservation.remove(reservation);
        reservation.setCourt(null);
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
        courtDTO other = (courtDTO) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cancha [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
}