package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class PlayDate {

    private int playDateId;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private Date playdateDate;
    private Time playdateTime;
    private String playdateDescription;
    private String statusType;
    private int invitedId;
    private int hostId;



    // getters & setters
    public int getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(int playDateId) {
        this.playDateId = playDateId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Date getPlaydateDate() {
        return playdateDate;
    }

    public void setPlaydateDate(Date playdateDate) {
        this.playdateDate = playdateDate;
    }

    public Time getPlaydateTime() {
        return playdateTime;
    }

    public void setPlaydateTime(Time playdateTime) {
        this.playdateTime = playdateTime;
    }

    public String getPlaydateDescription() {
        return playdateDescription;
    }

    public void setPlaydateDescription(String playdateDescription) {
        this.playdateDescription = playdateDescription;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public int getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(int invitedId) {
        this.invitedId = invitedId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
}
