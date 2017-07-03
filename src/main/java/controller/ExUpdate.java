package controller;

import exeption.InvalidException;
import model.DBconnection;
import model.Update;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate implements Command {
      private DataInOut dataInOut;
      private DBconnection dBconnection;

    ExUpdate(DataInOut dataInOut, DBconnection dBconnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("update");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Update query:");
        String updatemsg = dataInOut.inPut();
        try(Statement statement = dBconnection.getStatement()) {
            new Update(statement, updatemsg);
        } catch (SQLException e) {
            new InvalidException("Update ERROR", e);
        }
    }
}
