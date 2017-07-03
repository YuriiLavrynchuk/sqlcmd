package controller;

import exeption.InvalidException;
import model.DbConnection;
import view.Console;
import view.DataInOut;

public class Main {
    public static void main(String[] args) {
        DataInOut dataInOut = new Console();
        DbConnection dBconnection = new DbConnection();
        try {
            new MainController(dataInOut, dBconnection).run();
        } catch (InvalidException e) {
            e.getMessage();
        }
    }
}
