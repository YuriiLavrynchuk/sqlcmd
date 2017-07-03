package controller;

import view.DataInOut;

public class ExHelp implements  Command{

    private final DataInOut dataInOut;

    public ExHelp(DataInOut dataInOut) {

        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("help");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Exist command:\r\n" +
                             "connect       - connection to database\r\n" +
                             "select        - query from table\r\n" +
                             "tablelist     - getting names all tables\r\n" +
                             "update        - update rows in the table\r\n" +
                             "insert        - insert new row in the table\r\n" +
                             "delete        - delete row from table\r\n" +
                             "exit          - exit from application\r\n" +
                             "get columns   - get columns from table");
    }
}