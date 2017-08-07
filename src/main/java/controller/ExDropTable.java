package controller;

import exeption.InvalidException;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;

public class ExDropTable implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final InsertUpdateDeleteCreate crud;

    public ExDropTable(DataInOut dataInOut, DbConnection dBconnection, InsertUpdateDeleteCreate crud) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("drop_table");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tableName:");
        String tableName = dataInOut.inPut();

        String insertMsg = "DROP TABLE " + tableName;
        try (Statement statement = dBconnection.getStatement()){
                crud.run(statement, insertMsg);
                dataInOut.outPut("Table dropped");
        } catch (SQLException e){
            new InvalidException("ExDropTable ERROR", e);
        }
    }
}
