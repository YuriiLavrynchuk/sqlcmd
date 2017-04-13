package controller;

import view.DataInOut;

import java.sql.Connection;

/**
 * Created by Admin on 11.04.2017.
 */
public class MainController {
    private DataInOut dataInOut;
    private ExConnectToDB connect;
    private Connection connection;

    public MainController(DataInOut dataInOut){
        this.dataInOut = dataInOut;
//        this.connect = connect;
    }

    public MainController start(){
        connectToDB();
        return null;
    }

    private void connectToDB() {
        dataInOut.outPut("Hello!");
        for (int i = 0; i < 10 ; i++) {
            try{
                dataInOut.outPut("Please insert dbname:");
                String dbname = dataInOut.inPut();
                dataInOut.outPut("Please insert username:");
                String username = dataInOut.inPut();
                dataInOut.outPut("Please insert password:");
                String password = dataInOut.inPut();
                connection = new ExConnectToDB(dbname, username, password).getConnect();
                dataInOut.outPut("Connection success!");
                break;
            }catch(Exception e){
                dataInOut.outPut(e.getMessage());
            };
        }
        dataInOut.outPut("Please insert command:");
        String command = dataInOut.inPut();
        if(command.equals("select")){
            dataInOut.outPut("Insert tablename:");
            String message = dataInOut.inPut();
            ExSelect select = new ExSelect(connection, message);
        }
    }
}