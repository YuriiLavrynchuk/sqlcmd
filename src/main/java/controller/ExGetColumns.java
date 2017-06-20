package controller;

import model.Select;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExGetColumns implements Command {
    private final Connection connection;
    private DataInOut dataInOut;

    public ExGetColumns(DataInOut dataInOut, Connection connection){
        this.dataInOut = dataInOut;
        this.connection = connection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("get columns");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Enter tablename:");
        String selectmsg = dataInOut.inPut();
        String[] select = new String[0];
        try (Statement statement = connection.createStatement()){
            select = new Select(statement).getTableColumns(selectmsg);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
