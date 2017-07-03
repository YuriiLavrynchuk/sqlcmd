package controller;

import exeption.InvalidException;

interface Command {

    boolean checkCommand (String command);

    void execute(String command) throws InvalidException;
}
