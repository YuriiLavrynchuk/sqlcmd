package controller;

import exeption.InvalidException;
import model.DBconnection;
import model.Delete;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExDelete implements Command {
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    ExDelete(DataInOut dataInOut, DBconnection dBconnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("delete");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Delete query:");
        String deletemsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            new Delete(statement, deletemsg);
        } catch (SQLException e) {
            new InvalidException("ExDelete delete ERROR", e);
        }
    }
}

