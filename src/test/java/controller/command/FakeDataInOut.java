package controller.command;

import view.DataInOut;

public class FakeDataInOut implements DataInOut {

    private String message = "";

    @Override
    public void outPut(String msg) {
        message += msg;
    }

    @Override
    public String inPut() {
        return null;
    }

    public String getContent() {
        return message;
    }
}
