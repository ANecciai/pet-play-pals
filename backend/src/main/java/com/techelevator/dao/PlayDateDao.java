package com.techelevator.dao;

import com.techelevator.model.PlayDate;

import java.util.List;

public interface PlayDateDao {

    PlayDate getPlayDateByPlaydateId(int playDateId);

    List<PlayDate> listAllPlayDates();

    List<PlayDate> getAllPlayDatesByUsername(String username);

    List<PlayDate> getAllPlayDatesByPetId(int petId);

    void createPlayDate(String username, String address, String city, int zipCode, String date, String time);

    void updatePlayDate(PlayDate playDate,int playDateId);

    void deletePlayDate(PlayDate playDate, int playDateId);

}
