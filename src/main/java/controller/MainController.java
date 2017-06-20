package controller;

import view.DataInOut;

import java.sql.Connection;

public class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;
    private Connection connection;

    public MainController(DataInOut dataInOut, Connection connection) {
        this.dataInOut = dataInOut;
        this.connection = connection;

        this.commands = new Command[] {
                new ExExit(dataInOut),
                new ExHelp(dataInOut),
                new ExTableList(dataInOut, connection),
                new ExUpdate(dataInOut, connection),
                new ExInsert(dataInOut, connection),
                new ExDelete(dataInOut, connection),
                new ExSelect(dataInOut, connection),
                new ExGetColumns(dataInOut, connection),
                new ExNotExistCommand(dataInOut)
        };
    }

    public void start(){
        if (connection != null) {
//            TODO придумать выход из цикла
            while(true) {

                dataInOut.outPut("Please enter command or help:");
                String intput = dataInOut.inPut();

                for(Command command : commands){
                    if(command.checkCommand(intput)){
                        command.execute(intput);
                        break;
                    }
                }
            }
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