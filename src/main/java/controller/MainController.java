package controller;

import exeption.ExitExeption;
import model.DBconnection;
import view.DataInOut;

import java.sql.Connection;

public class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;

    public MainController(DataInOut dataInOut, DBconnection dbConnection) {
        this.dataInOut = dataInOut;

        this.commands = new Command[] {
                new ExConnectToDB(dataInOut, dbConnection),
                new ExHelp(dataInOut),
                new ExExit(dataInOut),
                new CheckConnection(dataInOut, dbConnection),
                new ExTableList(dataInOut, dbConnection),
                new ExUpdate(dataInOut, dbConnection),
                new ExInsert(dataInOut, dbConnection),
                new ExDelete(dataInOut, dbConnection),
                new ExSelect(dataInOut, dbConnection),
                new ExGetColumns(dataInOut, dbConnection),
                new ExNotExistCommand(dataInOut)
        };
    }

    public void run() {
        try {
            start();
        } catch (ExitExeption e){
            //do nothing
        }
    }

    public void start() {

        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect'");

            while (true) {
                String intput = dataInOut.inPut();

                for (Command command : commands) {
                    if (command.checkCommand(intput)) {
                        command.execute(intput);
                        break;
                    }
                }
                dataInOut.outPut("Please enter command or help:");
            }
        }
//            TODO придумать выход из цикла


    private void printError(Exception exeption) {
        String eMessage = exeption.getMessage();
        Throwable cause = exeption.getCause();
        if (cause != null) {
            eMessage += " " + cause.getMessage();
        }
        dataInOut.outPut("FAIL! Cause: " + eMessage);
        dataInOut.outPut("Try again.");
    }
}