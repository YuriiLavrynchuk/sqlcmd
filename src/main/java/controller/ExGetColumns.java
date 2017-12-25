package controller;

/**
 * Класс комманды "get_columns".
 * Выводит названия колонок из запрошенной таблицы.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import exeption.InvalidException;
import model.DbConnection;
import model.Select;
import view.DataInOut;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class ExGetColumns implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final Select select;

    /**
     * Создаёт объект комманды "get_columns".
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     * @param select объект выборки данных
     */
    public ExGetColumns(DataInOut dataInOut, DbConnection dbConnection, Select select){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.select = select;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("get_columns");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tableName:");
        String selectMsg = dataInOut.inPut();
        String[] sel = new String[0];
        //запуск процедуры возврата списка колонок
        try (Statement statement = dBconnection.getStatement()){
            sel = select.getTableColumns(selectMsg, statement);
        } catch (SQLException e){
            new InvalidException("ExGetColumns ERROR", e);
        }
        dataInOut.outPut(Arrays.toString(sel));
    }
}
