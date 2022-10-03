package com.example.onliner.repository.mapper;

import com.example.onliner.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    private static final String USER_ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String ROLE = "role";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(USER_ID));
        user.setFirstName(rs.getString(FIRST_NAME));
        user.setLastName(rs.getString(LAST_NAME));
        user.setEmail(rs.getString(EMAIL));
        user.setUsername(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSWORD));
        user.setDateOfBirth(rs.getDate(DATE_OF_BIRTH).toLocalDate());
        user.setRole(User.UserRole.valueOf(rs.getString(ROLE)));
        return user;
    }
}
