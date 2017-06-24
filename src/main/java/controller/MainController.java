package controller;

import view.DataInOut;

import java.sql.Connection;

public class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;
//    private final ExConnectToDB exConnectToDB;
    private Connection connection;

    public MainController(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
//        exConnectToDB= new ExConnectToDB(dataInOut);

        this.commands = new Command[] {
                new ExConnectToDB(dataInOut),
                new ExExit(dataInOut),
                new ExHelp(dataInOut),
                new CheckConnection(dataInOut),
//                new ExTableList(dataInOut, connection),
//                new ExUpdate(dataInOut, connection),
//                new ExInsert(dataInOut, connection),
//                new ExDelete(dataInOut, connection),
//                new ExSelect(dataInOut, connection),
//                new ExGetColumns(dataInOut, connection),
                new ExNotExistCommand(dataInOut)
        };
    }

    public void start(){
        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command connect");
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