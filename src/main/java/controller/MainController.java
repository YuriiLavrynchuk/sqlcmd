package controller;

import exeption.ExitException;
import exeption.InvalidException;
import model.DbConnection;
import view.DataInOut;

class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;

    public MainController(DataInOut dataInOut, DbConnection dBconnection) {
        this.dataInOut = dataInOut;

        this.commands = new Command[] {
                new ExConnectToDB(dataInOut, dBconnection),
                new ExHelp(dataInOut),
                new ExExit(dataInOut),
                new CheckConnection(dataInOut, dBconnection),
                new ExTableList(dataInOut, dBconnection),
                new ExUpdate(dataInOut, dBconnection),
                new ExInsert(dataInOut, dBconnection),
                new ExDelete(dataInOut, dBconnection),
                new ExSelect(dataInOut, dBconnection),
                new ExGetColumns(dataInOut, dBconnection),
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

    private void start() throws InvalidException, ExitException {

        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect'");

            while (true) {
                String intPut = dataInOut.inPut();
                for (Command command : commands) {
                    try {
                    if (command.checkCommand(intPut)) {
                        command.execute(intPut);
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