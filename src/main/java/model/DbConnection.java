package model;

/**
 * Класс предназначен для непосредственного подключения к БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import exeption.InvalidException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Создаёт объект класса.
 */
public class DbConnection {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    private Connection connection;

    /**
     * Метод проверяет входящие параметры на наличие null
     * @param dbName
     * @param username
     * @param password
     * @return возвращает false если присутствует null и true если нет
     */
    private boolean checkParam(String dbName, String username, String password){

        return !(dbName == null ||
                username == null ||
                password == null);
    }

    /**
     * Метод создаёт подключение к БД
     * @param dbName
     * @param username
     * @param password
     * @return возвращает объект с типом Connection
     * @throws InvalidException
     * @throws SQLException
     */
    public Connection connection(String dbName, String username, String password) throws InvalidException, SQLException {
        String url = DATABASE_URL + dbName + "?loggerLevel=OFF";

        if (!checkParam(dbName, username, password)){
            throw new InvalidException("Invalid incoming parameter:" + " dbname: "
                    + dbName + "username: " + username
                    + " password: " + password, new RuntimeException());
        }
        else {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e){
                System.out.println("You should add JDBC jar to project.");
            }
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    /**
     * Метод проверяет наличие соединения к БД
     * @return false если соединение отсутствует и true если соединение уствновлено
     */
    public boolean checkConnection(){
        return connection != null;
    }

    /**
     * Метод возвращает объект с типом Statement для работы с БД
     * @return Statement
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Метод закрывает соединение
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }
}

