package controller;

import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 11.04.2017.
 */
public class MainController {
    private DataInOut dataInOut;
    private Connection connection;

    public MainController(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    public MainController start(){
        connectToDB();

        while(true){
            dataInOut.outPut("Please insert command or help:");
            String command = dataInOut.inPut();

            if(command.equals("select")){
                dataInOut.outPut("Insert tablename:");
                String message = dataInOut.inPut();
                ExSelect select = new ExSelect(connection, message).select();
            } else if (command.equals("help")){
                doHelp();   
            } else if (command.equals("exit")){
                doExit();
                System.exit(0);
            }
        }
    }

    private void doHelp() {
        dataInOut.outPut("Exist command:");
        dataInOut.outPut("select");
        dataInOut.outPut("      query from table");
        dataInOut.outPut("tablelist");
        dataInOut.outPut("      getting names all tables");
        dataInOut.outPut("exit");
        dataInOut.outPut("      exit from aplication");
    }

    private void printError(Exception exeption) {
        String eMessage = /*e.getClass().getSimpleName() + ": " + */ exeption.getMessage();
        Throwable cause = exeption.getCause();
        if(cause != null){
            eMessage += " " + /*cause.getClass().getSimpleName() + ": " + */ cause.getMessage();
        }
        dataInOut.outPut("FAIL! Cause: " + eMessage);
        dataInOut.outPut("Try again.");
    }

    private void doExit() {
        dataInOut.outPut("Good by!");
        try {
            connection.close();
        } catch (SQLException e) {
            printError(e);
        }
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
                break;
            }catch(Exception e){
                printError(e);
            }
        }
        dataInOut.outPut("Connection success!");
    }
}