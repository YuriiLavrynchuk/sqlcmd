package controller;

/**
 * Класс комманды "delete".
 * Проверяет вводимую комманду, считывает и запускает выполнение sql-запроса удаления строк.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import exeption.InvalidException;
import model.DbConnection;
import model.InsertUpdateDeleteCreate;
import view.DataInOut;
import java.sql.SQLException;
import java.sql.Statement;

public class ExDelete implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private InsertUpdateDeleteCreate crud;

    /**
     * Создаёт объект комманды "delete" .
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     * @param crud объект выполняющий запросы к БД
     */
    public ExDelete(DataInOut dataInOut, DbConnection dbConnection, InsertUpdateDeleteCreate crud){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("delete");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Delete query in format ->\r\n" +
                "delete from tablename where column = 'value'\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String deleteMsg = dataInOut.inPut();
        //Проверка и выполнение введённого запроса
        try (Statement statement = dBconnection.getStatement()){
            if (checkQuery(deleteMsg)) {
                crud.run(statement, deleteMsg);
                dataInOut.outPut("Row deleted");
            }
        } catch (SQLException e){
            new InvalidException("ExDelete delete ERROR", e);
        }
    }

    /**
     * Метод проверяет наличие в запросе ключевых слов "delete from".
     * @param query
     * @return false если не словосочетания нет, true если словосочетание есть
     */
    private boolean checkQuery(String query){
        String word = "";
        try {
            word = query.substring(0, 11);
        } catch (Exception e){
            word = query.substring(0, query.length());
        }
        if (word.equals("delete from")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + query);
        return false;
    }
}

