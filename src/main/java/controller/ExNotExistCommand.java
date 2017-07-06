package controller;

import view.DataInOut;

public class ExNotExistCommand implements Command {
    private final DataInOut dataInOut;
    public ExNotExistCommand(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command){
        return true;
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Not exists command.");
    }
}
