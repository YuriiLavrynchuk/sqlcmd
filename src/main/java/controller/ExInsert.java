package controller;

import model.Insert;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExInsert implements Command{
    private DataInOut dataInOut;
    private Connection connection;

    public ExInsert(DataInOut dataInOut, Connection connection) {
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {

        return command.equals("insert");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Insert query:");
        String insertmsg = dataInOut.inPut();
        try (Statement statement = connection.createStatement()){
            new Insert(statement, insertmsg);
            dataInOut.outPut("Row inserted");
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
//            e.printStackTrace();
        }
    }
}
