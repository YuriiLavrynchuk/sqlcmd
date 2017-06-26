package controller;

import controller.MainController;
import exeption.InvalidException;
import model.DBconnection;
import view.Console;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        ExConnectToDB connect = new ExConnectToDB("postgres", "postgres", "1234");
//        ExSelect select = new ExSelect(connect.getDbname(), connect.getUsername(), connect.getPassword()).select();

        DataInOut dataInOut = new Console();
        DBconnection dbConnection = new DBconnection();
        new MainController(dataInOut, dbConnection).start();
    }
}
