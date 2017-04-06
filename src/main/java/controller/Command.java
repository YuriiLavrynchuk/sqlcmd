package controller;

import exeption.InvalidException;

import java.sql.SQLException;

/**
 * Created by Admin on 03.04.2017.
 */
public interface Command {
    public void execute() throws SQLException, InvalidException, ClassNotFoundException;
}
