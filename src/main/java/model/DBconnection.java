package model;

import controller.CommandConnectToDB;
import exeption.InvalidException;
import java.sql.*;
import java.util.Arrays;

public class DBconnection {
    private CommandConnectToDB commandConnectToDB;

    public DBconnection(CommandConnectToDB commandConnectToDB) throws SQLException {
        this.commandConnectToDB = commandConnectToDB;
    }

    public boolean checkParametrs(){
        //TODO добавить проверки на ошибочные параметры
            if(commandConnectToDB.getDbname() == null || commandConnectToDB.getUsername() == null || commandConnectToDB.getPassword() == null) return false;
            else return true;
    }

    public Statement dbConnection() throws SQLException, ClassNotFoundException, InvalidException {

        Statement statement;
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: " + commandConnectToDB.getDbname() + "username: " + commandConnectToDB.getUsername() + " password: " + commandConnectToDB.getPassword());
        else {
            Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(commandConnectToDB.getUrl(), commandConnectToDB.getUsername(), commandConnectToDB.getPassword());
            System.out.println("Соединение установлено");
            statement = connection.createStatement();

//            InsertMethod(statement);
            SelectAllTablesMethod(statement);
//            SelectMethod(statement);
//            UpdateMethod(statement);
//            DeleteMethod(statement);

            //закрываем соединение
            connection.close();
        }
        return statement;
    }

    //DELETE
    private void DeleteMethod(Statement statement) throws SQLException {
        statement.executeUpdate("DELETE FROM users where id = '4'");
        statement.close();
    }

    //UPDATE
    private void UpdateMethod(Statement statement) throws SQLException {
        statement.executeUpdate("UPDATE users SET name = 'user4' where id = '3'");
        statement.close();
    }

    //SELECT * tables
    private void SelectAllTablesMethod(Statement statement) throws SQLException {
        ResultSet select = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");
        String[] tables = new String[100];
        int index = 0;
        while (select.next()) {
//            System.out.println("Номер в выборке #" + select.getRow()
//                    + "\t" + select.getString("table_name"));
        tables[index++] = select.getString("table_name");
        }
        tables = Arrays.copyOf(tables, index + 1, String[].class);
        select.close();
        statement.close();
    }

    //SELECT
    private void SelectMethod(Statement statement) throws SQLException {
        ResultSet select = statement.executeQuery("SELECT * FROM users");
        while (select.next()){
            System.out.println("Номер в выборке #" + select.getRow()
                    + "\t" + select.getInt("id")
                    + "\t" + select.getString("name")
                    + "\t" + select.getString("password"));
        }
        select.close();
        statement.close();
    }

    //INSERT
    private void InsertMethod(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO users VALUES('4', 'user3', '1258')");
        statement.close();
    }
}

