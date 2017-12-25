package controller;

/**
 * Класс предназначен для проверки наличия соединения к БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

import model.DbConnection;
import view.DataInOut;

public class CheckConnection implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;

    /**
     * Конструктор
     *
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     */
    public CheckConnection(DataInOut dataInOut, DbConnection dbConnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
    }

    @Override
    public boolean checkCommand(String command){
        return !dBconnection.checkConnection();
    }

    @Override
    public void execute(String command){
        dataInOut.outPut(String.format("You can't use command '%s', please first connect to database using command 'connect'", command));
    }
}
