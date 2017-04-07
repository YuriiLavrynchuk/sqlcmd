package model;

import controller.CommandConnectToDB;
import exeption.InvalidException;
import java.sql.*;

public class DBconnection {
    private CommandConnectToDB commandConnectToDB;
    private Connection connection;

    public DBconnection(CommandConnectToDB commandConnectToDB){
        this.commandConnectToDB = commandConnectToDB;
    }

    private boolean checkParametrs(){
        //TODO добавить проверки на ошибочные параметры
            if(commandConnectToDB.getDbname() == null || commandConnectToDB.getUsername() == null || commandConnectToDB.getPassword() == null) return false;
            else return true;
    }

    public Connection dbConnection() throws InvalidException {
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + commandConnectToDB.getDbname() + "username: " + commandConnectToDB.getUsername()
                    + " password: " + commandConnectToDB.getPassword());
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("You should add JDBC jar to project.");
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(commandConnectToDB.getUrl(),
                        commandConnectToDB.getUsername(), commandConnectToDB.getPassword());
                System.out.println("Соединение установлено");
            } catch (SQLException e){
                System.out.println("Can't get connection to database:" + commandConnectToDB.getDbname());
                e.printStackTrace();
                connection = null;
            }
        }
        return connection;
    }

}

