package com.sena.crud_basic.model;
import java.io.Serializable;
import java.util.Objects;

public class ReservationId implements Serializable {
    // Estos nombres deben coincidir exactamente con los nombres 
    // de los campos @Id en tu clase reservationDTO
    private Long idReservation;
    private Long idClient;
    
    // Constructor sin argumentos (requerido)
    public ReservationId() {}
    
    public ReservationId(Long idReservation, Long idClient) {
        this.idReservation = idReservation;
        this.idClient = idClient;
    }
    
    // Getters y setters
    public Long getIdReservation() {
        return idReservation;
    }
    
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }
    
    public Long getIdClient() {
        return idClient;
    }
    
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
    
    // Métodos equals y hashCode (obligatorios)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationId that = (ReservationId) o;
        return Objects.equals(idReservation, that.idReservation) && 
               Objects.equals(idClient, that.idClient);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idClient);
    }
}