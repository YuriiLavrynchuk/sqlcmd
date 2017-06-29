package controller;

import model.DBconnection;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExGetColumns implements Command {
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    public ExGetColumns(DataInOut dataInOut, DBconnection dBconnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
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
        try (Statement statement = dBconnection.getStatement()){
            select = new Select(statement, dataInOut).getTableColumns(selectmsg);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
