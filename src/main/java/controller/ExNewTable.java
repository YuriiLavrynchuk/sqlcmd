package controller;

/**
 * Класс комманды "new_table".
 * Проверяет вводимую комманду, считывает и запускает выполнение sql-запроса создания новой таблицы.
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

public class ExNewTable implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final InsertUpdateDeleteCreate crud;

    /**
     * Создаёт объект комманды "new_table" .
     * @param dataInOut объект ввода/вывода
     * @param dBconnection объект подключения к БД
     * @param crud объект выполняющий запросы к БД
     */
    public ExNewTable(DataInOut dataInOut, DbConnection dBconnection, InsertUpdateDeleteCreate crud) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("new_table");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter create table query in format ->\r\n" +
                "create table tableName (column_name_1 column_type_1 ,..., column_name_n column_type_n)\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String insertMsg = dataInOut.inPut();
        //Проверка и выполнение введённого запроса
        try (Statement statement = dBconnection.getStatement()){
            if(checkQuery(insertMsg)) {
                crud.run(statement, insertMsg);
                dataInOut.outPut("Table created");
            }
        } catch (SQLException e){
            new InvalidException("ExCreateTable ERROR", e);
        }
    }

    /**
     * Метод проверяет наличие в запросе ключевых слов "create table".
     * @param query
     * @return false если не словосочетания нет, true если словосочетание есть
     */
    private boolean checkQuery(String query){
        String word = "";
        try {
            word = query.substring(0, 12);
        } catch (Exception e){
            word = query.substring(0, query.length());
        }
        if (word.equals("create table")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + query);
        return false;
    }
}
