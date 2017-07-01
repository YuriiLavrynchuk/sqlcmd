package controller;

import exeption.InvalidException;
import model.DBconnection;
import view.Console;
import view.DataInOut;

public class Main {
    public static void main(String[] args) {
        DataInOut dataInOut = new Console();
        DBconnection dbConnection = new DBconnection();
        try {
            new MainController(dataInOut, dbConnection).run();
        } catch (InvalidException e) {
            e.getMessage();
        }
    }
}
