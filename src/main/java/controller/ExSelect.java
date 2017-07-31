package controller;

import dnl.utils.text.table.TextTable;
import exeption.InvalidException;
import model.DbConnection;
import model.DataSet;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

//    @Override
//    public void execute(String command){
//        dataInOut.outPut("Enter tablename:");
//        String selectMsg = dataInOut.inPut();
//
//        try (Statement statement = dBconnection.getStatement()){
//            String[] tableColumns = select.getTableColumns(selectMsg, statement);
//            printHeader(tableColumns);
//            DataSet[] tableData = select.select(selectMsg, statement);
//            printTable(tableData);
//        } catch (SQLException e){
//            new InvalidException("ExSelect select Error", e);
//        }
//    }


//    @Override
//    //использование List
//    public void execute(String command){
//        dataInOut.outPut("Enter tablename:");
//        String selectMsg = dataInOut.inPut();
//
//        try (Statement statement = dBconnection.getStatement()){
//            String[] tableColumns = select.getTableColumns(selectMsg, statement);
//            List<String> tableData = select.select2(selectMsg, statement);
//        } catch (SQLException e){
//            new InvalidException("ExSelect select Error", e);
//        }
//    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tablename:");
        String selectMsg = dataInOut.inPut();

        try (Statement statement = dBconnection.getStatement()){
            String[] tableColumns = select.getTableColumns(selectMsg, statement);
//            printHeader(tableColumns);
            List<String> tableData = select.select2(selectMsg, statement);
//            dataInOut.outPut(Arrays.toString(tableData));
//            dataInOut.outPut(tableData.get(1).toString());
//            printTable(tableData);
//            getFields(tableData);

            String[][] table = new String[1][];
            dataInOut.outPut(tableData.get(1));
            table[0] = tableData.get(0).split(",");
            TextTable tt = new TextTable(tableColumns, table);
            tt.printTable();
        } catch (SQLException e){
            new InvalidException("ExSelect select Error", e);
        }
    }

    public String[][] getFields(List<String> listdata) {
        String[][] x = new String[1][];

        x[0] = listdata.get(0).split(", ");

//        int index = 0;
//        for (String s : listdata) {
//            x[index] = Arrays.toString(listdata.get(index).split(", "));
//            index++;
//        }
        return x;
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
}
