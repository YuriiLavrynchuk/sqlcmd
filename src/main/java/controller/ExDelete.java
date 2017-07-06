package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Delete;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExDelete implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    ExDelete(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("delete");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Delete query:");
        String deleteMsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            new Delete(statement, deleteMsg);
        } catch (SQLException e){
            new InvalidException("ExDelete delete ERROR", e);
        }
    }
}

