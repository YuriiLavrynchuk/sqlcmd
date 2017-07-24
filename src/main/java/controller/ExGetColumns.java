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
    private final Select select;

    public ExGetColumns(DataInOut dataInOut, DbConnection dbConnection, Select select){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.select = select;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("get columns");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tablename:");
        String selectMsg = dataInOut.inPut();
        String[] sel = new String[0];
        try (Statement statement = dBconnection.getStatement()){
            sel = select.getTableColumns(selectMsg, statement);
        } catch (SQLException e){
            new InvalidException("ExGetColumns get columns error", e);
        }
        dataInOut.outPut(Arrays.toString(sel));
    }
}
