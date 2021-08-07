package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlayDate getPlayDateByPlaydateId(int playDateId) {
        String sql = "SELECT * FROM playdate WHERE playdate_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateId);
        if(results.next()) {
            return mapRowToPlayDate(results);
        } else {
            throw new RuntimeException("playDate id" + playDateId + "was not found.");
        }
    }

    @Override
    public List<PlayDate> listAllPlayDates() {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT * FROM playdate";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            playDates.add(playDate);
        }
        return playDates;
    }

    @Override
    public List<PlayDate> getAllPlayDatesByUsername(String username) {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT * FROM playdate WHERE username = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while(results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            playDates.add(playDate);
        }
    return playDates;
    }

    @Override
    public List<PlayDate> getAllPlayDatesByPetId(int petId) {
        List<PlayDate> playDates = new ArrayList<>();
        String sql = "SELECT * FROM playdate INNER JOIN playdate_pets USING (playdate_id) WHERE pet_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
        while(results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            playDates.add(playDate);
        }
        return playDates;
    }

    @Override
    public void createPlayDate(PlayDate playdate) {
        String insertPlayDate = "INSERT INTO playdate (address, city, state," +
                " zip_code, playdate_time, playdate_date, playdate_description, status_type, host_id, invited_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertPlayDate, playdate.getAddress(), playdate.getCity(), playdate.getState(), playdate.getZipCode(),
                playdate.getPlaydateTime(), playdate.getPlaydateDate(), playdate.getPlaydateDescription(), playdate.getStatusType(), playdate.getHostId(), playdate.getInvitedId());
    }

    @Override
    public void updatePlayDate(PlayDate playdate, int playDateId) {
        String updatePlaydate = "UPDATE playdate SET address, city, state," +
                " zip_code, playdate_time, playdate_date, playdate_description, status_type, host_id, invited_id WHERE playdate_id = ?";
        jdbcTemplate.update(updatePlaydate, playdate.getAddress(), playdate.getCity(), playdate.getState(), playdate.getZipCode(),
                playdate.getPlaydateTime(), playdate.getPlaydateDate(), playdate.getPlaydateDescription(), playdate.getStatusType(), playdate.getHostId(), playdate.getInvitedId(), playDateId);
    }

    @Override
    public void deletePlayDate(PlayDate playDate, int playDateId) {
        String deletePlaydate = "SELECT * FROM playdate WHERE username = ?";
        jdbcTemplate.update(deletePlaydate, playDateId);
    }

    private PlayDate mapRowToPlayDate(SqlRowSet rs) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(rs.getInt("playdate_id"));
        playDate.setAddress(rs.getString("address"));
        playDate.setCity(rs.getString("city"));
        playDate.setState(rs.getString("state"));
        playDate.setZipCode(rs.getInt("zip_code"));
        playDate.setPlaydateDate(rs.getDate("playdate_date"));
        playDate.setPlaydateTime(rs.getTime("playdate_time"));
        playDate.setPlaydateDescription(rs.getString("playdate_description"));
        playDate.setStatusType(rs.getString("status_type"));
        playDate.setHostId(rs.getInt("host_id"));
        playDate.setInvitedId(rs.getInt("invited_id"));
        return playDate;
    }
}
