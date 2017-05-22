package controller;

import model.SelectTablesList;
import view.DataInOut;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class MainController {
    private final DataInOut dataInOut;
    private Connection connection;

    public MainController(DataInOut dataInOut) {
        this.dataInOut = dataInOut;
    }

    public void start(){

        while (!connectToDB()){
            if(connection != null){
                break;
            }
        }

        if (connection != null) {
//            TODO придумать выход из цикла
            while(true) {
                dataInOut.outPut("Please enter command or help:");
                String command = dataInOut.inPut();
                switch (command) {
                    case "select":
                        doSelect();
                        break;
                    case "delete":
                        doDelete();
                        break;
                    case "insert":
                        doInsert();
                        break;
                    case "update":
                        doUpdate();
                        break;
                    case "tablelist":
                        doTableList();
                        break;
                    case "help":
                        doHelp();
                        break;
                    case "exit":
                        doExit();
                        System.exit(0);
                }
            }
        }
    }

    private void doDelete() {
        dataInOut.outPut("Enter Delete query:");
        String deletemsg = dataInOut.inPut();
        new ExDelete(connection, deletemsg).delete();
    }

    private void doInsert() {
        dataInOut.outPut("Enter Insert query:");
        String insertmsg = dataInOut.inPut();
        new ExInsert(connection, insertmsg).insert();
    }

    private void doUpdate() {
        dataInOut.outPut("Enter Update query:");
        String updatemsg = dataInOut.inPut();
        new ExUpdate(connection, updatemsg).update();
    }

    private void doSelect(){
         dataInOut.outPut("Enter tablename:");
         String selectmsg = dataInOut.inPut();
         new ExSelect(connection, selectmsg).select();
    }

    private void doTableList() {
        try {
            String[] tablesList = new SelectTablesList(connection.createStatement()).selectAllTable();
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
        dataInOut.outPut("delete    - delete row from table");
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
                connection = new ExConnectToDB(dbname, username, password).getConnection();
            }catch (Exception e){
                connection = null;
//                e.printStackTrace();
                return false;
            }
        return connection != null;
    }
}