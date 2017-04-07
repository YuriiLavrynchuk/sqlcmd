package controller;

import exeption.InvalidException;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Admin on 03.04.2017.
 */
public interface Command {
    public Statement execute() throws SQLException, InvalidException, ClassNotFoundException;
}
