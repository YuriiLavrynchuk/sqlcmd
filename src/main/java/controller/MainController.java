package controller;

import exeption.ExitException;
import exeption.InvalidException;
import model.DbConnection;
import model.Select;
import view.DataInOut;

class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;

    public MainController(DataInOut dataInOut, DbConnection dBconnection){
        this.dataInOut = dataInOut;

        this.commands = new Command[] {
                new ExConnectToDB(dataInOut, dBconnection),
                new ExHelp(dataInOut),
                new ExExit(dataInOut, dBconnection),
                new CheckConnection(dataInOut, dBconnection),
                new ExTableList(dataInOut, dBconnection),
                new ExUpdate(dataInOut, dBconnection),
                new ExInsert(dataInOut, dBconnection),
                new ExDelete(dataInOut, dBconnection),
                new ExSelect(dataInOut, dBconnection, new Select()),
                new ExGetColumns(dataInOut, dBconnection),
                new ExNotExistCommand(dataInOut)
        };
    }

    public void run() throws InvalidException {
        try {
            start();
        } catch (ExitException e){
        }
    }

    private void start() throws InvalidException, ExitException {

        dataInOut.outPut("Hello!");
        dataInOut.outPut("Please connect to database using command 'connect'");

        while (true){
            String intPut = dataInOut.inPut();
            for (Command command : commands){
                try {
                    if (command.checkCommand(intPut)){
                        command.execute(intPut);
                        break;
                    }
                } catch (ExitException e1){
                    throw e1;
                } catch (Exception e){
                    throw new InvalidException("ERROR", e);
                }
            }
            dataInOut.outPut("Please enter command or help:");
        }
    }
}