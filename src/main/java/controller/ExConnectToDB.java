package controller;

import model.DBconnection;
import view.DataInOut;

import java.sql.Connection;

public class ExConnectToDB implements Command{
    private final DataInOut dataInOut;
    private Connection connection;

    public ExConnectToDB(DataInOut dataInOut){
        this.dataInOut = dataInOut;
        connection = null;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("connect");
    }

    @Override
    public void execute(String command) {
        connect();
    }

    private Connection connect(){
        while (connection == null) {
//            dataInOut.outPut("Hello!");
            dataInOut.outPut("Please insert dbname:");
            String dbname = dataInOut.inPut();
            dataInOut.outPut("Please insert username:");
            String username = dataInOut.inPut();
            dataInOut.outPut("Please insert password:");
            String password = dataInOut.inPut();
            try {
                connection = new DBconnection(dbname, username, password).dbConnection();
                dataInOut.outPut("Connection success!");
            } catch (Exception e) {
                connection = null;
//                e.printStackTrace();
            }
        }
        return connection;
    }

    public boolean checkConnection(){
        return connection != null;
    }
}
