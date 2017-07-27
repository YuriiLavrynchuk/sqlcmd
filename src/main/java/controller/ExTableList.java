package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.SelectTablesList;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExTableList implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final SelectTablesList selectTablesList;

    ExTableList(DataInOut dataInOut, DbConnection dbConnection, SelectTablesList selectTablesList){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.selectTablesList = selectTablesList;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) {
        try(Statement statement = dBconnection.getStatement()){
            String[] tablesList = selectTablesList.selectAllTable(statement);
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e){
            new InvalidException("ERROR SelectTablesList", e);
        }
    }
}