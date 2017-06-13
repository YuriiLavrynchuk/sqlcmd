package controller;

import model.SelectTablesList;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class ExTableList implements Command{

    private DataInOut dataInOut;

    ExTableList(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {

        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) {}

    @Override
    public void execute(String command, Connection connection) {
        try {
            String[] tablesList = new SelectTablesList(connection.createStatement()).selectAllTable();
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }
}