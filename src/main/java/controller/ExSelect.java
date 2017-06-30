package controller;

import model.DBconnection;
import model.DataSet;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExSelect implements Command {
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    public ExSelect(DataInOut dataInOut, DBconnection dBconnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("select");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter tablename:");
        String selectmsg = dataInOut.inPut();

        Statement statement = null;
        try {
            statement = dBconnection.getStatement();
        } catch (SQLException e) {

        }
        Select select = new Select(statement, dataInOut);
        String[] tableColumns = select.getTableColumns(selectmsg);
        printHeader(tableColumns);

        DataSet[] tableData = null;
        try {
            tableData = select.select(selectmsg);
            printTable(tableData);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    private void printTable(DataSet[] tableData) {
        for (DataSet row : tableData) {
            printRow(row);
        }
        dataInOut.outPut("--------------------");
    }

    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "|";
        for (Object value : values) {
            result += value + "|";
        }
        dataInOut.outPut(result);
    }

    private void printHeader(String[] tableColumns) {
        String result = "|";
        for (String name : tableColumns) {
            result += name + "|";
        }
        dataInOut.outPut("--------------------");
        dataInOut.outPut(result);
        dataInOut.outPut("--------------------");
    }
}
