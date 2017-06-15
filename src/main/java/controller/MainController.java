package controller;

import view.DataInOut;

import java.sql.Connection;

public class MainController {
    private final DataInOut dataInOut;
    private final Command[] commands;
    private Connection connection;

    public MainController(DataInOut dataInOut) {
        this.dataInOut = dataInOut;

        this.commands = new Command[] {
                new ExExit(dataInOut),
                new ExHelp(dataInOut),
                new ExTableList(dataInOut),
                new ExUpdate(dataInOut),
                new ExInsert(dataInOut),
                new ExDelete(dataInOut)
        };
    }

    public void start(){

        while(!connectToDB()){
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
                } else if(commands[5].checkCommand(command)){
                    commands[5].execute(command, connection);
                } else if(commands[4].checkCommand(command)){
                    commands[4].execute(command, connection);
                } else if(commands[3].checkCommand(command)) {
                    commands[3].execute(command, connection);
                } else if (commands[2].checkCommand(command)) {
                    commands[2].execute(command, connection);
                } else if (commands[1].checkCommand(command)) {
                    commands[1].execute(command);
                } else if (commands[0].checkCommand(command)) {
                    commands[0].execute(command);
                }
            }
        }
    }

    private void doSelect(){
         dataInOut.outPut("Enter tablename:");
         String selectmsg = dataInOut.inPut();
         new ExSelect(connection, selectmsg).select();
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