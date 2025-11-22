package com.parkee.pos.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_PARKING")
public class Parking {

    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "VEHICLE_NO")
    private String vehicleNo;

    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;

    @Column(name = "CLOCK_IN")
    private LocalDateTime clockIn;

    @Column(name = "POS_CODE_IN")
    private String posCodeIn;

    @Column(name = "TRANSACTION_STATUS")
    private String transactionStatus;

    @Column(name = "DATE_CREATE")
    private LocalDateTime dateCreate;

    @Column(name = "USER_CREATE")
    private String userCreate;

    @Column(name = "DATE_UPD")
    private LocalDateTime dateUpd;

    @Column(name = "USER_UPD")
    private String userUpd;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getClockIn() {
        return clockIn;
    }

    public void setClockIn(LocalDateTime clockIn) {
        this.clockIn = clockIn;
    }

    public String getPosCodeIn() {
        return posCodeIn;
    }

    public void setPosCodeIn(String posCodeIn) {
        this.posCodeIn = posCodeIn;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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
