package model;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private String url = "jdbc:postgresql://localhost:5432/";
    private String dbname;
    private String username;
    private String password;

    public DBconnection(String dbname, String username, String password) {
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.url = url + dbname + "?loggerLevel=OFF";
    }

    private boolean checkParametrs(){

        return !(dbname == null ||
                username == null ||
                password == null);
    }

    public Connection dbConnection() throws InvalidException {
        Connection connection;
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + dbname + "username: " + username
                    + " password: " + password, new RuntimeException());
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("You should add JDBC jar to project.");
//                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection success!");
            } catch (SQLException e){
                throw new InvalidException("Can't get connection to database:" + username, e);
//                TODO стоит ли закрывать здесь соединение?
            }
        }
            return connection;
        }
    }

