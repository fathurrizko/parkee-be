package com.parkee.pos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_POS")
public class Pos {
    @Id
    @Column(name = "POS_CODE")
    private String posCode;

    @Column(name = "POS_LOCATION")
    private String posLocation;

    @Column(name = "POS_TYPE")
    private String posType;

    @Column(name = "PRICE_ID")
    private String priceId;

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

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosLocation() {
        return posLocation;
    }

    public void setPosLocation(String posLocation) {
        this.posLocation = posLocation;
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
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
