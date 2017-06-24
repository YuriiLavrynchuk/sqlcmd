package controller;

import model.DBconnection;
import model.DataSet;
import model.Select;
import view.DataInOut;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

class ExSelect implements Command {
    private DataInOut dataInOut;
    private DBconnection dBconnection;

    public ExSelect(DataInOut dataInOut, DBconnection dBconnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
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
        try (Statement statement = dBconnection.getStatement()){
            select = new Select(statement).select(selectmsg);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        System.out.println(Arrays.toString(select));
    }
}
