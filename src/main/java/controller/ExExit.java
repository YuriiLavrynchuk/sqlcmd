package controller;

import exeption.ExitException;
import exeption.InvalidException;
import model.DbConnection;
import view.DataInOut;

public class ExExit implements Command {

    private final DataInOut dataInOut;
    private DbConnection dBconnection;

    public ExExit(DataInOut dataInOut, DbConnection dBconnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("exit");
    }

    @Override
    public void execute(String command){
        try {
            close();
        } catch (InvalidException e) {
        }
        dataInOut.outPut("Good by!");
        throw new ExitException();
    }

    private void close() throws InvalidException {
        try {
            dBconnection.closeConnection();
        } catch (Exception e) {
            throw new InvalidException("closeConnection ERROR ", e);
        }
    }
}
