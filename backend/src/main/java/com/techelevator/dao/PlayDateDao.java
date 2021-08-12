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

    void acceptPlayDate(int playDateId);

    void declinePlayDate(int playDateId);

    void cancelPlayDate(int playDateId);

    void expirePlayDate(int playDateId);
}
