package controller;

import model.Delete;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExDelete implements Command {
    private DataInOut dataInOut;

    ExDelete(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("delete");
    }

    @Override
    public void execute(String command) {}

    @Override
    public void execute(String command, Connection connection) {
        dataInOut.outPut("Enter Delete query:");
        String deletemsg = dataInOut.inPut();
        try (Statement statement = connection.createStatement()){
            new Delete(statement, deletemsg);
        } catch (SQLException e) {
            System.out.println("ExDelete delete ERROR");
//            e.printStackTrace();
        }
    }
}

