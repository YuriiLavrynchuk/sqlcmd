package controller;

import exeption.ExitException;
import exeption.InvalidException;
import model.DbConnection;
import view.DataInOut;

/**
 * Класс комманды выхода "exit".
 * Проверяет вводимую комманду, считывает выход из программы.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */

public class ExExit implements Command {
    private final DataInOut dataInOut;
    private DbConnection dBconnection;

    /**
     * Создаёт объект комманды "exit".
     * @param dataInOut объект ввода/вывода
     * @param dBconnection объект подключения к БД
     */
    public ExExit(DataInOut dataInOut, DbConnection dBconnection){
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("exit");
    }

    @Override
    public void execute(String command){
        try {
            close();
        } catch (InvalidException e) {
        }
        dataInOut.outPut("Good by!");
        throw new ExitException();
    }

    /**
     * Инкапсулированній метод, который запускает закрытие соединения.
     * @throws InvalidException
     */
    private void close() throws InvalidException {
        try {
            dBconnection.closeConnection();
        } catch (Exception e) {
            throw new InvalidException("closeConnection ERROR ", e);
        }
    }
}
