package controller;

import model.Update;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ExUpdate implements Command {
      private DataInOut dataInOut;
      private Connection connection;

    ExUpdate(DataInOut dataInOut, Connection connection) {
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("update");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter Update query:");
        String updatemsg = dataInOut.inPut();
        try(Statement statement = connection.createStatement()) {
            new Update(statement, updatemsg);
        } catch (SQLException e) {
            System.out.println("ExUpdate update ERROR");
//            e.printStackTrace();
        }
    }
}
