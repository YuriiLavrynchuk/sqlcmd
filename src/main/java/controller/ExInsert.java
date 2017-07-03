package controller;

import exeption.InvalidException;
import model.DBconnection;
import model.Insert;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExInsert implements Command{
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    public ExInsert(DataInOut dataInOut, DBconnection dBconnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("insert");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Insert query:");
        String insertmsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            new Insert(statement, insertmsg);
        } catch (SQLException e) {
            new InvalidException("ExInsert insert ERROR", e);
        }
    }
}
