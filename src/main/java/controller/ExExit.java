package controller;

import view.DataInOut;

/**
 * Created by yuriy.lavrinchuk on 12.06.2017.
 */
public class ExExit implements Command {

    private DataInOut dataInOut;

    public ExExit(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command) {
        return command.equals("exit");
    }

    @Override
    public void execute(String command) {
        dataInOut.outPut("Good by!");
        System.exit(0);
    }
}
