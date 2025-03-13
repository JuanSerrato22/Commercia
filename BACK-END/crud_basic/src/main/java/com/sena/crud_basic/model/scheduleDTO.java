package com.sena.crud_basic.model;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Entidad que representa un horario disponible para una cancha
 */
@Entity
@Table(name = "schedule")
public class scheduleDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    
    @Column(name = "court_id", nullable = false)
    private Long courtId;
    
    @Column(name = "weekday", nullable = false, length = 20)
    private String weekday;
    
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    
    @Column(name = "disponible")
    private boolean available;
    
    // Constructor vacío requerido por JPA
    public scheduleDTO() {
    }
    
    // Constructor con parámetros
    public scheduleDTO(Long courtId, String weekday, LocalTime startTime, LocalTime endTime, boolean available) {
        this.courtId = courtId;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.available = available;
    }
    
    // Getters y Setters
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getCourtId() {
        return courtId;
    }

    public void setCanchaId(Long courtId) {
        this.courtId = courtId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
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

    public void setHoraCierre(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        scheduleDTO schedule = (scheduleDTO) o;
        return scheduleId.equals(schedule.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId);
    }

    // Método toString para representar el objeto como String
    @Override
    public String toString() {
        return "schedule{" +
                "scheduleId=" + scheduleId +
                ", courtId=" + courtId +
                ", weekday='" + weekday + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", available=" + available +
                '}';
    }
    
    // Métodos para operaciones CRUD (sin usar patrón DAO)
    public void guardar(EntityManager em) {
        if (this.scheduleId == null) {
            em.persist(this);
        } else {
            em.merge(this);
        }
    }
    
    public static scheduleDTO buscarPorId(EntityManager em, Long id) {
        return em.find(scheduleDTO.class, id);
    }
    
    public static List<scheduleDTO> listarTodos(EntityManager em) {
        return em.createQuery("SELECT h FROM Horario h", scheduleDTO.class).getResultList();
    }
    
    public static List<scheduleDTO> buscarPorCancha(EntityManager em, Long canchaId) {
        return em.createQuery("SELECT h FROM Horario h WHERE h.canchaId = :canchaId", scheduleDTO.class)
                .setParameter("canchaId", canchaId)
                .getResultList();
    }
    
    public static List<scheduleDTO> buscarPorDia(EntityManager em, String diaSemana) {
        return em.createQuery("SELECT h FROM Horario h WHERE h.diaSemana = :diaSemana", scheduleDTO.class)
                .setParameter("diaSemana", diaSemana)
                .getResultList();
    }
    
    public void eliminar(EntityManager em) {
        scheduleDTO schedule = em.find(scheduleDTO.class, this.scheduleId);
        if (schedule != null) {
            em.remove(schedule);
        }
    }
}