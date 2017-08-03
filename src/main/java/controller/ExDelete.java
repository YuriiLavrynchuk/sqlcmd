package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

public class ExDelete implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private InsertUpdateDeleteCreate crud;

    public ExDelete(DataInOut dataInOut, DbConnection dbConnection, InsertUpdateDeleteCreate crud){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.crud = crud;
    }

    ExDelete(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("delete");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Delete query in format -> delete from tablename where column = 'value'\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String deleteMsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
                crud.run(statement, deleteMsg);
                dataInOut.outPut("Row deleted");
        } catch (SQLException e){
            new InvalidException("ExDelete delete ERROR", e);
        }
    }
}

