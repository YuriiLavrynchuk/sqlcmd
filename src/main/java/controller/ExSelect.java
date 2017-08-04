package controller;

import dnl.utils.text.table.TextTable;
import exeption.InvalidException;
import model.DataSet;
import model.DbConnection;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExSelect implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private Select select;

    public ExSelect(DataInOut dataInOut, DbConnection dbConnection, Select select) {
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.select = select;
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
            String[] tableColumns = select.getTableColumns(selectMsg, statement);
            List<String> tableData = select.select(selectMsg, statement);
            printerTables(tableColumns, getFields(tableData));
        } catch (SQLException e){
            new InvalidException("ExSelect select Error", e);
        }
    }

    public String[][] getFields(List<String> listdata) {
        String[][] array = new String[listdata.size()][];
        int index = 0;
        for (String row: listdata) {
            array[index] = listdata.get(index).split(", ");
            index++;
        }
        return array;
    }

    public void printTable(DataSet[] tableData){
        for (DataSet row : tableData){
            printRow(row);
        }
        dataInOut.outPut("--------------------");
    }

    public void printRow(DataSet row){
        Object[] values = row.getValues();

        StringBuilder result = new StringBuilder("|");
        for (Object value : values) {
            result.append(value).append("|");
        }
        dataInOut.outPut(result.toString());
    }

    public void printHeader(String[] tableColumns){

        StringBuilder result = new StringBuilder("|");
        for (String name : tableColumns){
            result.append(name).append("|");
        }

        dataInOut.outPut("--------------------");
        dataInOut.outPut(result.toString());
        dataInOut.outPut("--------------------");
    }

    public void printerTables (String[] columns, String[][] data){
        TextTable textTable = new TextTable(columns, data);
        textTable.printTable();
    }
}
