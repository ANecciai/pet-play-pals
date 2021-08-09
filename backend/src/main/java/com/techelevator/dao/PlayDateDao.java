package com.techelevator.dao;

import com.techelevator.model.PlayDate;

import java.util.List;

public interface PlayDateDao {

    PlayDate getPlayDateByPlaydateId(int playDateId);

    List<PlayDate> listAllPlayDates();

    List<PlayDate> getAllPlayDatesByUsername(String username);

    List<PlayDate> getAllPlayDatesByPetId(int petId);

    void createPlayDate(PlayDate playDate);

    void updatePlayDate(PlayDate playDate,int playDateId);

    void deletePlayDate(PlayDate playDate, int playDateId);

    void acceptPlayDate(PlayDate playDate, int playDateId);

    void declinePlayDate(PlayDate playDate, int playDateId);

    void cancelPlayDate(PlayDate playDate, int playDateId);
}
