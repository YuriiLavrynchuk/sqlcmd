package controller;

import java.sql.Connection;

public interface Command {

    boolean checkCommand (String command);

    void execute(String command);

    void execute(String command, Connection connection);
}
