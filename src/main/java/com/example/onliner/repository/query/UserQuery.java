package com.example.onliner.repository.query;

public final class UserQuery {

    public static final String INSERT_USER = """
            INSERT INTO onliner_users (first_name, last_name, email, username, password, date_of_birth, role)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    public static final String SELECT_USER_BY_USERNAME = """
            SELECT id, first_name, last_name, email, username, password, date_of_birth,  role FROM onliner_users
            WHERE username = (?);
            """;
}
