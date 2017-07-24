package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExGetColumns implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    public ExGetColumns(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("get columns");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tablename:");
        String selectMsg = dataInOut.inPut();
        String[] select = new String[0];
        try (Statement statement = dBconnection.getStatement()){
            select = new Select().getTableColumns(selectMsg, statement);
        } catch (SQLException e){
            new InvalidException("ExGetColumns get columns error", e);
        }
        dataInOut.outPut(Arrays.toString(select));
    }
}
