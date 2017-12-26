package controller;

/**
 * Класс комманды "update".
 * Проверяет вводимую комманду, считывает и выполняет sql-запрос обновления строк в таблице.
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

class ExUpdate implements Command {
      private final DataInOut dataInOut;
      private final DbConnection dBconnection;
      private final InsertUpdateDeleteCreate crud;

    /**
     * Создаёт объект комманды "update".
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     * @param crud объект выполняющий запросы к БД.
     */
    ExUpdate(DataInOut dataInOut, DbConnection dbConnection, InsertUpdateDeleteCreate crud){
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("update");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter Update query in format ->\r\n" +
                "update tablename set column = value where column_n = value_n\r\n" +
                "Remember! If you use textwords like values you must wrap these words in quotes: 'textword'");
        String updateMsg = dataInOut.inPut();
        //Проверка и выполнение введённого запроса
        try(Statement statement = dBconnection.getStatement()){
            if (checkQuery(updateMsg)) {
                crud.run(statement, updateMsg);
                dataInOut.outPut("Row updated");
            }
        } catch (SQLException e){
            new InvalidException("Update ERROR", e);
        }
    }

    /**
     * Метод проверяет наличие в запросе ключевого слова "update".
     * @param query
     * @return false если не словосочетания нет, true если словосочетание есть
     */
    private boolean checkQuery(String query){
        String word = "";
        try {
            word = query.substring(0, 6);
        } catch (Exception e){
            word = query.substring(0, query.length());
        }
        if (word.equals("update")){
            return true;
        }
        dataInOut.outPut("Wrong query: " + query);
        return false;
    }
}
