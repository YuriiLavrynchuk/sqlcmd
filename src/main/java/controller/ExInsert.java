package controller;

/**
 * Класс комманды "insert".
 * Проверяет вводимую комманду, считывает и запускает выполнение sql-запроса вставки строк в таблицу.
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

class ExInsert implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final InsertUpdateDeleteCreate crud;

    /**
     * Создаёт объект комманды "insert" .
     * @param dataInOut объект ввода/вывода
     * @param dBconnection объект подключения к БД
     * @param crud объект выполняющий запросы к БД
     */
    public ExInsert(DataInOut dataInOut, DbConnection dBconnection, InsertUpdateDeleteCreate crud) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("insert");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Insert query in format ->\r\n" +
                "insert into tablename values(value_1,... value_n)\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String insertMsg = dataInOut.inPut();
        //Проверка и выполнение введённого запроса
        try (Statement statement = dBconnection.getStatement()){
            if(checkQuery(insertMsg)) {
                crud.run(statement, insertMsg);
                dataInOut.outPut("Row inserted");
            }
        } catch (SQLException e){
            new InvalidException("ExInsert insert ERROR", e);
        }
    }

    /**
     * Метод проверяет наличие в запросе ключевых слов "insert into".
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
        if (word.equals("insert into")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + word);
        return false;
    }
}
