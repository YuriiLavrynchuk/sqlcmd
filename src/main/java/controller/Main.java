package controller;

/**
 * Класс который запускает приложение.
 * для запуска в этом классе создються обекты DataInOut и DbConnection,
 * которые передаються в MainController
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

import exeption.InvalidException;
import model.DbConnection;
import view.Console;
import view.DataInOut;

public class Main {
    public static void main(String[] args){
        //создание объекта ввода/вывода и объекта подключения к БД
        DataInOut dataInOut = new Console();
        DbConnection dBconnection = new DbConnection();
        //передаём созданные объекты в MainController и запускаем метод run()
        try {
            new MainController(dataInOut, dBconnection).run();
        } catch (InvalidException e){
            e.getMessage();
        }
    }
}
