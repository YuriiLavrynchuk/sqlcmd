package controller;

import view.DataInOut;

public class ExHelp implements  Command{

    private DataInOut dataInOut;

    public ExHelp(DataInOut dataInOut) {

        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("help");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Exist command:");
        dataInOut.outPut("select        - query from table");
        dataInOut.outPut("tablelist     - getting names all tables");
        dataInOut.outPut("update        - update rows in the table");
        dataInOut.outPut("insert        - insert new row in the table");
        dataInOut.outPut("delete        - delete row from table");
        dataInOut.outPut("exit          - exit from application");
        dataInOut.outPut("get columns   - get columns from table");
    }
}