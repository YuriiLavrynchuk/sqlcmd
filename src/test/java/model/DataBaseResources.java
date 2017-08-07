package model;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseResources extends DbConnection {

    public static final String DB_NAME_LOCAL = "postgres";
    public static final String USER_NAME_LOCAL = "postgres";
    public static final String PASSWORD_LOCAL = "1234";

    public static final String DB_NAME_TEST = "test";
    public static final String USER_NAME_TEST = "admin";
    public static final String PASSWORD_TEST = "pass";
    private Connection local;
    private Connection test;

    public void before() throws SQLException, InvalidException {

        local = connection(DB_NAME_LOCAL, USER_NAME_LOCAL, PASSWORD_LOCAL);

        try(Statement statement = local.createStatement()) {
            statement.execute("DROP DATABASE IF EXISTS test");
            statement.execute("DROP ROLE IF EXISTS admin");
            statement.execute("CREATE ROLE " + USER_NAME_TEST + " LOGIN " + " PASSWORD " +
                    "'" + PASSWORD_TEST + "'" + " SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION");
            statement.executeUpdate("CREATE DATABASE " + DB_NAME_TEST);

            closeConnection(local);
        }

        test = connection(DB_NAME_TEST, USER_NAME_TEST, PASSWORD_TEST);

        try(Statement statement = test.createStatement()){
            statement.execute("CREATE TABLE users (id integer, name text, password text)");
            statement.execute("INSERT INTO users VALUES(1, 'Admin', '1111')");
            statement.execute("INSERT INTO users VALUES(2, 'User2', '4532')");
            statement.execute("INSERT INTO users VALUES(3, 'User3', '6443')");
            statement.execute("INSERT INTO users VALUES(4, 'User4', '4444')");
            statement.execute("INSERT INTO users VALUES(5, 'User5', '9999')");

            closeConnection(test);
        }
    }

    public void after() throws SQLException, InvalidException {
        local = connection(DB_NAME_LOCAL, USER_NAME_LOCAL, PASSWORD_LOCAL);

        try(Statement statement = local.createStatement()){
            statement.execute("DROP DATABASE IF EXISTS test");
            statement.execute("DROP ROLE IF EXISTS admin");
            closeConnection(local);
        }
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
