package controller;

import view.DataInOut;

public class CheckConnection implements Command {
    private DataInOut dataInOut;

    public CheckConnection(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        ExConnectToDB exConnectToDB = new ExConnectToDB(dataInOut);
        return exConnectToDB.checkConnection();
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("You can't use commands, please first connect to database");
    }
}
