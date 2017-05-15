package controller;

import exeption.InvalidException;
import model.SelectTablesList;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 11.04.2017.
 */
public class MainController {
    private DataInOut dataInOut;
    private Connection connection;

    public MainController(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
    }

    public MainController start(){

        while (!connectToDB()){
            connectToDB();
            if(connection != null){
                break;
            }
        }

        if (connection != null) {
//            TODO придумать выход из цикла
            while(true) {
                dataInOut.outPut("Please enter command or help:");
                String command = dataInOut.inPut();
                if (command.equals("select")) {
                    doSelect();
                } else if(command.equals("insert")){
                    doInsert();
                } else if(command.equals("update")) {
                    doUpdate();
                } else if (command.equals("tablelist")) {
                    doTableList();
                } else if (command.equals("help")) {
                    doHelp();
                } else if (command.equals("exit")) {
                    doExit();
                    System.exit(0);
                }
            }
        }
        return null;
    }

    private void doInsert() {
        dataInOut.outPut("Enter Insert query:");
        String insertmsg = dataInOut.inPut();
        ExInsert insert = new ExInsert(connection, insertmsg).insert();
    }

    private void doUpdate() {
        dataInOut.outPut("Enter Update query:");
        String updatemsg = dataInOut.inPut();
        ExUpdate update = new ExUpdate(connection, updatemsg).update();
    }

    private void doSelect(){
         dataInOut.outPut("Enter tablename:");
         String selectmsg = dataInOut.inPut();
         ExSelect select = new ExSelect(connection, selectmsg).select();
    }

    private void doTableList() {
        try {
            String[] tablesList = new SelectTablesList(connection.createStatement()).SelectAllTable();
            System.out.println(Arrays.toString(tablesList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doHelp() {
        dataInOut.outPut("Exist command:");
        dataInOut.outPut("select    - query from table");
        dataInOut.outPut("tablelist - getting names all tables");
        dataInOut.outPut("update    - update rows in the table");
        dataInOut.outPut("insert    - insert new row in the table");
        dataInOut.outPut("exit      - exit from application");
    }

    private void printError(Exception exeption) {
        String eMessage = /*e.getClass().getSimpleName() + ": " + */ exeption.getMessage();
        Throwable cause = exeption.getCause();
        if (cause != null) {
            eMessage += " " + /*cause.getClass().getSimpleName() + ": " + */ cause.getMessage();
        }
        dataInOut.outPut("FAIL! Cause: " + eMessage);
        dataInOut.outPut("Try again.");
    }

    private void doExit() {
        try {
            dataInOut.outPut("Good by!");
            connection.close();
        } catch (Exception e) {
            printError(e);
        }
    }

    private boolean connectToDB() {

        dataInOut.outPut("Hello!");
            dataInOut.outPut("Please insert dbname:");
            String dbname = dataInOut.inPut();
            dataInOut.outPut("Please insert username:");
            String username = dataInOut.inPut();
            dataInOut.outPut("Please insert password:");
            String password = dataInOut.inPut();
            try {
                connection = new ExConnectToDB(dbname, username, password).getConnect();
            }catch (Exception e){
                connection = null;
//                e.printStackTrace();
                return false;
            }
            if(connection != null){
                return true;
            }
            else{
                return false;
            }
    }
}