package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Insert;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExInsert implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    public ExInsert(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("insert");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Insert query:");
        String insertMsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            new Insert(statement, insertMsg);
        } catch (SQLException e){
            new InvalidException("ExInsert insert ERROR", e);
        }
    }
}
