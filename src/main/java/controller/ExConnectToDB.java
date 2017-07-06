package controller;

import exeption.InvalidException;
import model.DbConnection;
import view.DataInOut;

import java.sql.Connection;

public class ExConnectToDB implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    public ExConnectToDB(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("connect");
    }

    @Override
    public void execute(String command){
        connect();
    }

    private void connect(){
            dataInOut.outPut("Please insert dbname:");
            String dbName = dataInOut.inPut();
            dataInOut.outPut("Please insert username:");
            String username = dataInOut.inPut();
            dataInOut.outPut("Please insert password:");
            String password = dataInOut.inPut();
        Connection connection;
        try {
                connection = dBconnection.connection(dbName, username, password);
                dataInOut.outPut("Connection success!");
            } catch (Exception e){
                connection = null;
                new InvalidException("Can't get connection to database:" + dbName, e);
            }
    }
}
