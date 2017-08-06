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
        //get connection to local database
        local = connection(DB_NAME_LOCAL, USER_NAME_LOCAL, PASSWORD_LOCAL);

        try(Statement statement = local.createStatement()) {
            //drop exists database
            statement.execute("DROP DATABASE IF EXISTS test");
            //drop user
            statement.execute("DROP ROLE IF EXISTS admin");
            //create user with password
            statement.execute("CREATE ROLE " + USER_NAME_TEST + " LOGIN " + " PASSWORD " +
                    "'" + PASSWORD_TEST + "'" + " SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION");
            //create database
            statement.executeUpdate("CREATE DATABASE " + DB_NAME_TEST);
            //close connection to local database
            closeConnection(local);
        }
        //get connection to test database
        test = connection(DB_NAME_TEST, USER_NAME_TEST, PASSWORD_TEST);
        //create table and insert values
        try(Statement statement = test.createStatement()){
            statement.execute("CREATE TABLE users (id integer, name text, password text)");
            statement.execute("INSERT INTO users VALUES(1, 'Admin', '1111')");
            statement.execute("INSERT INTO users VALUES(2, 'User2', '4532')");
            statement.execute("INSERT INTO users VALUES(3, 'User3', '6443')");
            statement.execute("INSERT INTO users VALUES(4, 'User4', '4444')");
            statement.execute("INSERT INTO users VALUES(5, 'User5', '9999')");
            //close connection to test database
            closeConnection(test);
        }
    }

    public void after() throws SQLException, InvalidException {
        local = connection(DB_NAME_LOCAL, USER_NAME_LOCAL, PASSWORD_LOCAL);

        try(Statement statement = local.createStatement()){
            //drop database
            statement.execute("DROP DATABASE IF EXISTS test");
            //drop user
            statement.execute("DROP ROLE IF EXISTS admin");
            //close connection to local database
            closeConnection(local);
        }
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
