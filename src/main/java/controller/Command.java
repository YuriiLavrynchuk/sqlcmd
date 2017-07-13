package controller;

import exeption.InvalidException;

public interface Command {

    boolean checkCommand (String command);

    void execute(String command);
}
