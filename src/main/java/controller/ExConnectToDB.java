package controller;

/**
 * Класс комманды подключения к БД "connect".
 * Проверяет вводимую комманду и запускает подключение к БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

import exeption.InvalidException;
import model.DbConnection;
import view.DataInOut;

public class ExConnectToDB implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    /**
     * Создаёт объект комманды "connect".
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     */
    public ExConnectToDB(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("connect");
    }

    @Override
    public void execute(String command){
        connect();
    }

    /**
     * Инкапсулированный метод считывает dbname, username, password и запучкает подключение к БД
     */
    private void connect(){
            dataInOut.outPut("Please insert dbname:");
            String dbName = dataInOut.inPut();
            dataInOut.outPut("Please insert username:");
            String username = dataInOut.inPut();
            dataInOut.outPut("Please insert password:");
            String password = dataInOut.inPut();
        try {
            //запуск подключения
            dBconnection.connection(dbName, username, password);
            dataInOut.outPut("Connection success!");
        } catch (Exception e){
            new InvalidException("Can't get connection to database:" + dbName, e);
        }
    }
}
