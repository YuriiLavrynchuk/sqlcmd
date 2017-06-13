package controller;

import model.Insert;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExInsert implements Command{

    private DataInOut dataInOut;

    public ExInsert(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("insert");
    }

    @Override
    public void execute(String command) {}

    @Override
    public void execute(String command, Connection connection) {
        dataInOut.outPut("Enter Insert query:");
        String insertmsg = dataInOut.inPut();
        try (Statement statement = connection.createStatement()){
            new Insert(statement, insertmsg);
        } catch (SQLException e) {
            System.out.println("ExInsert insert ERROR");
//            e.printStackTrace();
        }
    }
}
