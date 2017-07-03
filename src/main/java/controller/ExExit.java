package controller;

import exeption.*;
import view.DataInOut;

public class ExExit implements Command {

    private final DataInOut dataInOut;

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
        throw new ExitException();
    }
}
