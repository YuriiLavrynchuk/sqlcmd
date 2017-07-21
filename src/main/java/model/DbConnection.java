package model;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    private Connection connection;

    private boolean checkParam(String dbName, String username, String password){

        return !(dbName == null ||
                username == null ||
                password == null);
    }

    public Connection connection(String dbName, String username, String password) throws InvalidException, SQLException {
        String url = DATABASE_URL + dbName + "?loggerLevel=OFF";

        if (!checkParam(dbName, username, password)){
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + dbName + "username: " + username
                    + " password: " + password, new RuntimeException());
        }
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e){
                System.out.println("You should add JDBC jar to project.");
            }
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public boolean checkConnection(){
        return connection != null;
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }
}

