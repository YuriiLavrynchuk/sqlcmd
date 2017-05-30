package model;

import controller.ExConnectToDB;
import exeption.InvalidException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private final ExConnectToDB exConnectToDB;

    public DBconnection(ExConnectToDB exConnectToDB){
        this.exConnectToDB = exConnectToDB;
    }

    private boolean checkParametrs(){

        return !(exConnectToDB.getDbname() == null ||
                exConnectToDB.getUsername() == null ||
                exConnectToDB.getPassword() == null);
    }

    public Connection dbConnection() throws InvalidException {
        Connection connection;
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + exConnectToDB.getDbname() + "username: " + exConnectToDB.getUsername()
                    + " password: " + exConnectToDB.getPassword(), new RuntimeException());
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("You should add JDBC jar to project.");
//                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(exConnectToDB.getUrl(),
                        exConnectToDB.getUsername(), exConnectToDB.getPassword());
                System.out.println("Connection success!");
            } catch (SQLException e){
//                System.out.println("Can't get connection to database:" + exConnectToDB.getDbname());
                throw new InvalidException("Can't get connection to database:" + exConnectToDB.getDbname(), e);
//                TODO стоит ли закрывать здесь соединение?
            }
        }
            return connection;
        }
    }

