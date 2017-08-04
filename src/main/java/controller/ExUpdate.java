package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate implements Command {
      private final DataInOut dataInOut;
      private final DbConnection dBconnection;
      private final InsertUpdateDeleteCreate crud;

    ExUpdate(DataInOut dataInOut, DbConnection dbConnection, InsertUpdateDeleteCreate crud){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("update");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Update query in format -> update tablename set column = value where column_n = value_n\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String updateMsg = dataInOut.inPut();
        try(Statement statement = dBconnection.getStatement()){
            if (checkQuery(updateMsg)) {
                crud.run(statement, updateMsg);
                dataInOut.outPut("Row updated");
            }
        } catch (SQLException e){
            new InvalidException("Update ERROR", e);
        }
    }

    private boolean checkQuery(String query){
        String word = "";
        try {
            word = query.substring(1, 6);
        } catch (Exception e){
            word = query.substring(1, query.length());
        }
        if (word.equals("insert into")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + query);
        return false;
    }
}
