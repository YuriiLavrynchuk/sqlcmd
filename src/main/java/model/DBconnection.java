package model;

import controller.ConnectToDB;
import exeption.InvalidException;

import java.sql.*;

//import ua.com.juja.sqlcmd.*;

/**
 * Created by Admin on 27.03.2017.
 */
public class DBconnection {
    private String url;
    private String dbname;
    private String username;
    private String password;

    public DBconnection(ConnectToDB connectToDB) throws SQLException {
        this.url = "jdbc:postgresql://localhost:5432/" + connectToDB.getDbname();
        this.dbname = connectToDB.getDbname();
        this.username = connectToDB.getUsername();
        this.password = connectToDB.getPassword();
    }

    public boolean checkParametrs(){
        //TODO добавить проверки на ошибочные параметры
            if(dbname == null || username == null || password == null || dbname == null) return false;
            else return true;
    }

    public Statement dbConnection() throws SQLException, ClassNotFoundException, InvalidException {

        Statement statement;
        if (!checkParametrs())
            throw new InvalidException("Invalid incoming parameter:" + " dbname: " + dbname + "username: " + username + " password: " + password);
        else {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение установлено");
            statement = connection.createStatement();

//            InsertMethod(statement);
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

    //SELECT
    private void SelectMethod(Statement statement) throws SQLException {
        ResultSet select = statement.executeQuery("select * from users");
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

    //TODO доваить реализацию закрытия моединения
}

