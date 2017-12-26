package controller;

/**
 * Класс комманды "tablelist".
 * Проверяет вводимую комманду, считывает и выполняет sql-запрос списка доступных таблиц.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import exeption.InvalidException;
import model.DbConnection;
import model.SelectTablesList;
import view.DataInOut;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExTableList implements Command{
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final SelectTablesList selectTablesList;

    /**
     * Создаёт объект комманды "select".
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     * @param selectTablesList объект выполняющий выборку списка таблиц.
     */
    ExTableList(DataInOut dataInOut, DbConnection dbConnection, SelectTablesList selectTablesList){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.selectTablesList = selectTablesList;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("tablelist");
    }

    @Override
    public void execute(String command) {
        try(Statement statement = dBconnection.getStatement()){
            String[] tablesList = selectTablesList.selectAllTable(statement);
            dataInOut.outPut(Arrays.toString(tablesList));
        } catch (SQLException e){
            new InvalidException("ERROR SelectTablesList", e);
        }
    }
}