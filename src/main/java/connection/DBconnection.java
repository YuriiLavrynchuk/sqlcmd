package connection;

import exeption.InvalidException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import ua.com.juja.sqlcmd.*;

/**
 * Created by Admin on 27.03.2017.
 */
public class DBconnection {
    private String url;
    private String dbname;
    private String username;
    private String password;

    public DBconnection(String dbname, String username, String password) throws SQLException {
        this.url = "jdbc:postgresql://localhost:5432/" + dbname;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public boolean checkParametrs(){

        //TODO добавить проверки на ошибочные параметры
        // проверка на null
            if(dbname == null || username == null || password == null || dbname == null) return false;
            else return true;
    }

    public DBconnection dbConnection() throws SQLException, ClassNotFoundException, InvalidException {

        Connection connection = null;
        if(!checkParametrs()) throw new InvalidException("Invalid incoming parameter:" + " dbname: "+ dbname + "username: " + username + " password: " + password);
       //1-й вариант
        /*  else{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success to: " + dbname);
        }*/

      //2-й вариант
        else
        try {
            //Загружаем драйвер
            Class.forName("org.postgresql.Driver");
//            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success to: " + dbname);
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet select1 = statement.executeQuery(
                    "SELECT * FROM users");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Output select");
            while (select1.next()) {
                System.out.println("Номер в выборке #" + select1.getRow()
                        + "\t id #" + select1.getInt("id")
                        + "\t" + select1.getString("name")
                        + "\t" + select1.getString("password"));
            }
            // Вставить запись
//            statement.executeUpdate(
//                    "INSERT INTO users(username) values('name')");
            //Обновить запись
//            statement.executeUpdate(
//                    "UPDATE users SET username = 'admin' where id = 1");



            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры
//            PreparedStatement preparedStatement = null;
            // ? - место вставки нашего значеня
//            preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM users where id > ? and id < ?");
            //Устанавливаем в нужную позицию значения определённого типа
//            preparedStatement.setInt(1, 2);
//            preparedStatement.setInt(2, 10);
            //выполняем запрос
//            ResultSet result2 = preparedStatement.executeQuery();
//
//            System.out.println("Выводим PreparedStatement");
//            while (result2.next()) {
//                System.out.println("Номер в выборке #" + result2.getRow()
//                        + "\t Номер в базе #" + result2.getInt("id")
//                        + "\t" + result2.getString("username"));
//            }
//
//            preparedStatement = connection.prepareStatement(
//                    "INSERT INTO users(username) values(?)");
//            preparedStatement.setString(1, "user_name");
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
//            preparedStatement.executeUpdate();



            //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
//            CallableStatement callableStatement = null;
            //Вызываем функцию myFunc (хранится в БД)
//            callableStatement = connection.prepareCall(
//                    " { call myfunc(?,?) } ");
            //Задаём входные параметры
//            callableStatement.setString(1, "Dima");
//            callableStatement.setString(2, "Alex");
            //Выполняем запрос
//            ResultSet result3 = callableStatement.executeQuery();
            //Если CallableStatement возвращает несколько объектов ResultSet,
            //то нужно выводить данные в цикле с помощью метода next
            //у меня функция возвращает один объект
//            result3.next();
//            System.out.println(result3.getString("MESSAGE"));
            //если функция вставляет или обновляет, то используется метод executeUpdate()

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
        //TODO доваить реализацию закрытия моединения
}

