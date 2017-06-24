package controller;

import model.DBconnection;
import view.DataInOut;

public class CheckConnection implements Command {
    private DataInOut dataInOut;
    private DBconnection dbConnection;

    public CheckConnection(DataInOut dataInOut, DBconnection dbConnection) {
        this.dataInOut = dataInOut;
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return !dbConnection.checkConnection();
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut(String.format("You can't use command '%s', please first connect to database using command 'connect'", command));
    }
}
