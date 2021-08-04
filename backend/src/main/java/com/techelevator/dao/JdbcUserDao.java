package com.techelevator.dao;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;
@Component
@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if(results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId "+userId+" was not found.");
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }


    @Override
    public List<User> findByZip(int zip){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE zip_code = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, zip);
        while(rowSet.next()){
            User user = mapRowToUser(rowSet);
            users.add(user);
        }
        return users;
    }


    public boolean create(String username, String password, String role) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    @Override
    public void updateUserAsAdmin(User user, Long userId) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, " +
                "role = ?, zip_code = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getAuthorities(), user.getZipCode(), userId);
    }

    @Override
    public void updateUser(Principal currentUser, User user){
        String sql = "UPDATE users SET username = ?, password_hash = ?, first_name = ?, last_name = ?, zip_code = ?, role = ? WHERE username = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getZipCode(), user.getAuthorities(), user, currentUser.getName());
    }


    @Override
    public void deleteUserAsAdmin(Long userId) {
        String deleteUser = ("DELETE FROM users WHERE user_id = ?");
        jdbcTemplate.update(deleteUser, userId);

    }

    @Override
    public void deleteUser(Principal currentUser){
    String deleteUser = ("DELETE FROM users WHERE username = ?");
    jdbcTemplate.update(deleteUser, currentUser.getName());}


    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setZipCode(rs.getInt("zip_code"));
        user.setActivated(true);
        return user;
    }
}
