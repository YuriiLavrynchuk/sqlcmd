package controller.command;

import view.DataInOut;

public class FakeDataInOut implements DataInOut {

    private String message = "";
    private String input = null;

    @Override
    public void outPut(String msg) {
        message += msg;
    }

    @Override
    public String inPut() {
        if (this.input == null){
            throw new IllegalStateException("You should initialize method 'inPut()'");
        }
        String result = this.input;
        this.input = null;
        return result;
    }

    public void addInPut(String input){
        this.input = input;
    }

    public String getContent() {
        return message;
    }
}
