package controller;

import exeption.ExitException;
import exeption.InvalidException;
import model.DBconnection;
import view.DataInOut;

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

    public void run() throws InvalidException {
        try {
            start();
        } catch (ExitException e){
            //do nothing
        }
    }

    public void start() throws InvalidException, ExitException {

        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect'");

            while (true) {
                String intput = dataInOut.inPut();

                for (Command command : commands) {
                    try {
                    if (command.checkCommand(intput)) {
                        command.execute(intput);
                        break;
                    }
                    } catch (Exception e) {
                        if (e instanceof ExitException) {
                            throw e;
                        }
                        throw new InvalidException("ERROR", e);
                    }
                }
                dataInOut.outPut("Please enter command or help:");
            }
        }
}