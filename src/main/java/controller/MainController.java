package controller;

import model.DBconnection;
import view.DataInOut;

import java.sql.Connection;

public class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;
//    private DBconnection dbConnection;
//    private final ExConnectToDB exConnectToDB;
    private Connection connection;

    public MainController(DataInOut dataInOut, DBconnection dbConnection) {
        this.dataInOut = dataInOut;
//        this.dbConnection = dbConnection;
//        exConnectToDB= new ExConnectToDB(dataInOut);

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

    public void start() {
        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect'");
//            TODO придумать выход из цикла

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