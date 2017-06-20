package controller;

import exeption.InvalidException;
import model.DBconnection;
import view.DataInOut;

import java.sql.Connection;

public class ExConnectToDB {
    private final DataInOut dataInOut;
    private Connection connection;

    public ExConnectToDB(DataInOut dataInOut) throws InvalidException {
        this.dataInOut = dataInOut;
    }

    public void connect() {
        while (connection == null) {
            dataInOut.outPut("Hello!");
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
    }

    public Connection getConnection(){
        connect();
        return connection;
    }
}
