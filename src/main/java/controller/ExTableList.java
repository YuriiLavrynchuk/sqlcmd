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

    ExTableList(DataInOut dataInOut, DbConnection dbConnection) {
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) throws InvalidException {
        try(Statement statement = dBconnection.getStatement()) {
            String[] tablesList = new SelectTablesList(statement).selectAllTable();
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e) {
            throw new InvalidException("ERROR SelectTablesList", e);
        }
    }
}