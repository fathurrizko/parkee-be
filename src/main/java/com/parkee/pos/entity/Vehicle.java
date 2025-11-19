package com.parkee.pos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_VEHICLE")
public class Vehicle {

    @Id
    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATE_CREATE")
    private LocalDateTime dateCreate;

    @Column(name = "USER_CREATE")
    private String userCreate;

    @Column(name = "DATE_UPD")
    private LocalDateTime dateUpd;

    @Column(name = "USER_UPD")
    private String userUpd;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public LocalDateTime getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(LocalDateTime dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getUserUpd() {
        return userUpd;
    }

    public void setUserUpd(String userUpd) {
        this.userUpd = userUpd;
    }
}
