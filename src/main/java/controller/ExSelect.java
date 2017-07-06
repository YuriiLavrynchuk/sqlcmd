package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.DataSet;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExSelect implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    public ExSelect(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("select");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tablename:");
        String selectMsg = dataInOut.inPut();

        try (Statement statement = dBconnection.getStatement()){
            Select select = new Select(statement, dataInOut);
            String[] tableColumns = select.getTableColumns(selectMsg);
            printHeader(tableColumns);
            DataSet[] tableData = select.select(selectMsg);
            printTable(tableData);
        } catch (SQLException e){
            new InvalidException("ExSelect select Error", e);
        }
    }

    private void printTable(DataSet[] tableData){
        for (DataSet row : tableData){
            printRow(row);
        }
        dataInOut.outPut("--------------------");
    }

    private void printRow(DataSet row){
        Object[] values = row.getValues();
        String result = "|";
        for (Object value : values){
            result += value + "|";
        }
        dataInOut.outPut(result);
    }

    private void printHeader(String[] tableColumns){
        String result = "|";
        for (String name : tableColumns){
            result += name + "|";
        }
        dataInOut.outPut("--------------------");
        dataInOut.outPut(result);
        dataInOut.outPut("--------------------");
    }
}
