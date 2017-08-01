package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Update;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate implements Command {
      private final DataInOut dataInOut;
      private final DbConnection dBconnection;
      private final Update update;

    ExUpdate(DataInOut dataInOut, DbConnection dbConnection, Update update){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.update = update;
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
            update.updateRun(statement, updateMsg);
            dataInOut.outPut("Row updated");
        } catch (SQLException e){
            new InvalidException("Update ERROR", e);
        }
    }
}
