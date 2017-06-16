package controller;

import model.DataSet;
import model.Select;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

class ExSelect implements Command {
    private DataInOut dataInOut;
    private Connection connection;

    public ExSelect(DataInOut dataInOut, Connection connection){
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("select");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter tablename:");
        String selectmsg = dataInOut.inPut();
        DataSet[] select = new DataSet[0];
        try (Statement statement = connection.createStatement()){
            select = new Select(statement).select(selectmsg);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
