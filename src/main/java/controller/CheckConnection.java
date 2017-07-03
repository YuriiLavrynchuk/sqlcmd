package controller;

import model.DbConnection;
import view.DataInOut;

public class CheckConnection implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    public CheckConnection(DataInOut dataInOut, DbConnection dbConnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return !dBconnection.checkConnection();
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut(String.format("You can't use command '%s', please first connect to database using command 'connect'", command));
    }
}
