package model;

import controller.CommandConnectToDB;
import exeption.InvalidException;
import java.sql.*;

public class DBconnection {
    private CommandConnectToDB commandConnectToDB;
    private Connection connection;

    public DBconnection(CommandConnectToDB commandConnectToDB) throws SQLException {
        this.commandConnectToDB = commandConnectToDB;
    }

    public boolean checkParametrs(){
        //TODO добавить проверки на ошибочные параметры
            if(commandConnectToDB.getDbname() == null || commandConnectToDB.getUsername() == null || commandConnectToDB.getPassword() == null) return false;
            else return true;
    }

    public Connection dbConnection() throws SQLException, ClassNotFoundException, InvalidException {
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + commandConnectToDB.getDbname() + "username: " + commandConnectToDB.getUsername()
                    + " password: " + commandConnectToDB.getPassword());
        else {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(commandConnectToDB.getUrl(),
                    commandConnectToDB.getUsername(), commandConnectToDB.getPassword());
            System.out.println("Соединение установлено");
        }
        return connection;
    }

}

