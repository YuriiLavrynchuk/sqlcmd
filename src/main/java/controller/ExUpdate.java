package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Update;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate implements Command {
      private final DataInOut dataInOut;
      private final DbConnection dBconnection;

    ExUpdate(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("update");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Update query:");
        String updateMsg = dataInOut.inPut();
        try(Statement statement = dBconnection.getStatement()){
            new Update(statement, updateMsg);
        } catch (SQLException e){
            new InvalidException("Update ERROR", e);
        }
    }
}
