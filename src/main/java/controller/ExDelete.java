package controller;

import model.Delete;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExDelete implements Command {
    private DataInOut dataInOut;
    private Connection connection;

    ExDelete(DataInOut dataInOut, Connection connection){
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("delete");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Delete query:");
        String deletemsg = dataInOut.inPut();
        try (Statement statement = connection.createStatement()){
            new Delete(statement, deletemsg);
            dataInOut.outPut("Row deleted");
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
//            e.printStackTrace();
        }
    }
}

