package com.sena.crud_basic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "clientCourt")
public class clientcourtDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient")
    private int idClient;

    @Column(name = "idCourt")
    private int idCourt;

    @Column(name = "userRole")
    private String userRole;

    @Column(name = "assignamentDate")
    private String assignamentDate;

    @Column(name = "privileges")
    private String privileges;


    // Getters and Setters

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdCourt() {
        return idCourt;
    }

    public void setIdCourt(int idCourt) {
        this.idCourt = idCourt;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAssignamentDate() {
        return assignamentDate;
    }

    public void setAssignamentDate(String assignamentDate) {
        this.assignamentDate = assignamentDate;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
}
