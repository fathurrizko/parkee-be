package com.parkee.pos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_MEMBER")
public class Member {

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Id
    @Column(name = "VEHICLE_NO")
    private String vehicleNo;

    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @Column(name = "VEHICLE_UNIT")
    private String vehicleUnit;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getVehicleUnit() {
        return vehicleUnit;
    }

    public void setVehicleUnit(String vehicleUnit) {
        this.vehicleUnit = vehicleUnit;
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
