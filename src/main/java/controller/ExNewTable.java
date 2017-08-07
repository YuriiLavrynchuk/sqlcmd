package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

public class ExNewTable implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final InsertUpdateDeleteCreate crud;

    public ExNewTable(DataInOut dataInOut, DbConnection dBconnection, InsertUpdateDeleteCreate crud) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("new_table");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter create table query in format ->\r\n" +
                "create table tableName (column_name_1 column_type_1 ,..., column_name_n column_type_n)\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String insertMsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            if(checkQuery(insertMsg)) {
                crud.run(statement, insertMsg);
                dataInOut.outPut("Table created");
            }
        } catch (SQLException e){
            new InvalidException("ExCreateTable ERROR", e);
        }
    }

    private boolean checkQuery(String query){
        String word = "";
        try {
            word = query.substring(0, 12);
        } catch (Exception e){
            word = query.substring(0, query.length());
        }
        if (word.equals("create table")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + query);
        return false;
    }
}
