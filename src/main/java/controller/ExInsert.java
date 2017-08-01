package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.Insert;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

class ExInsert implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final Insert insert;

    public ExInsert(DataInOut dataInOut, DbConnection dBconnection, Insert insert) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.insert = insert;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("insert");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Insert query in format -> insert into tablename values(value_1,... value_n)\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String insertMsg = dataInOut.inPut();
        try (Statement statement = dBconnection.getStatement()){
            insert.insertRun(statement, insertMsg);
            dataInOut.outPut("Row inserted");
        } catch (SQLException e){
            new InvalidException("ExInsert insert ERROR", e);
        }
    }
}
