package controller;

import model.DBconnection;
import model.SelectTablesList;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExTableList implements Command{
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    ExTableList(DataInOut dataInOut, DBconnection dBconnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) {
        try(Statement statement = dBconnection.getStatement()) {
            String[] tablesList = new SelectTablesList(statement).selectAllTable();
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}