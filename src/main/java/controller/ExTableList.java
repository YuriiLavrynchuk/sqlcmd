package controller;

import model.SelectTablesList;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class ExTableList implements Command{

    private DataInOut dataInOut;
    private Connection connection;

    public ExTableList(DataInOut dataInOut, Connection connection) {
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) {
        try {
            String[] tablesList = new SelectTablesList(connection.createStatement()).selectAllTable();
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }
}