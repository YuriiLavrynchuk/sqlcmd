package controller;

public interface Command {

    boolean checkCommand (String command);

    void execute(String command);
}
