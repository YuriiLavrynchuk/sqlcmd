package model;

import controller.ExConnectToDB;
import exeption.InvalidException;
import java.sql.*;

public class DBconnection {
    private ExConnectToDB exConnectToDB;
    private Connection connection;

    public DBconnection(ExConnectToDB exConnectToDB){
        this.exConnectToDB = exConnectToDB;
    }

    private boolean checkParametrs(){
        //TODO добавить проверки на ошибочные параметры
            if(exConnectToDB.getDbname() == null ||
                    exConnectToDB.getUsername() == null ||
                    exConnectToDB.getPassword() == null) return false;
            else return true;
    }

    public Connection dbConnection() throws InvalidException {
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + exConnectToDB.getDbname() + "username: " + exConnectToDB.getUsername()
                    + " password: " + exConnectToDB.getPassword());
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("You should add JDBC jar to project.");
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(exConnectToDB.getUrl(),
                        exConnectToDB.getUsername(), exConnectToDB.getPassword());
                System.out.println("Соединение установлено");
            } catch (SQLException e){
                System.out.println("Can't get connection to database:" + exConnectToDB.getDbname());
                e.printStackTrace();
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return connection;
    }
}

