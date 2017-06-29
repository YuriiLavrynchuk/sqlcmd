package controller;

import model.DBconnection;
import view.DataInOut;

import java.sql.Connection;

public class ExConnectToDB implements Command{
    private final DataInOut dataInOut;
    private Connection connection;
    private DBconnection dbConnection;

    public ExConnectToDB(DataInOut dataInOut, DBconnection dbConnection){
        this.dataInOut = dataInOut;
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("connect");
    }

    @Override
    public void execute(String command) {
        connect();
    }

    public Connection connect(){
//        while (connection == null) {
            dataInOut.outPut("Please insert dbname:");
            String dbname = dataInOut.inPut();
            dataInOut.outPut("Please insert username:");
            String username = dataInOut.inPut();
            dataInOut.outPut("Please insert password:");
            String password = dataInOut.inPut();
            try {
                connection = dbConnection.connection(dbname, username, password);
                dataInOut.outPut("Connection success!");
            } catch (Exception e) {
                connection = null;
//                e.printStackTrace();
            }
        return connection;
    }
}
