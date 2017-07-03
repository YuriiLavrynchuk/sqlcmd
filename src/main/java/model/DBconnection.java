package model;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {
    private Connection connection;
    private Statement statement;

    private boolean checkParametrs(String dbname, String username, String password){

        return !(dbname == null ||
                username == null ||
                password == null);
    }

    public Connection connection(String dbname, String username, String password) throws InvalidException {
        String url = "jdbc:postgresql://localhost:5432/" + dbname + "?loggerLevel=OFF";

        if (!checkParametrs(dbname, username, password))
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + dbname + "username: " + username
                    + " password: " + password, new RuntimeException());
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("You should add JDBC jar to project.");
            }
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e){
                throw new InvalidException("Can't get connection to database:" + username, e);
            }
        }
        return connection;
    }

    public boolean checkConnection(){
        return connection != null;
    }

    public Statement getStatement() throws SQLException {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw e;
        }
        return statement;
    }
}

