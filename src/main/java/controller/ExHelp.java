package controller;

/**
 * Класс комманды "help". Выводит список доступных комманд.
 * Проверяет вводимую комманду, считывает и выполняет sql-запрос.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import view.DataInOut;

public class ExHelp implements  Command{
    private final DataInOut dataInOut;

    /**
     * Создаёт объект комманды "help".
     * @param dataInOut объект ввода/вывода
     */
    public ExHelp(DataInOut dataInOut){
        this.dataInOut = dataInOut;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("help");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("You should use commandwords and follow the messages in the console\r\n" +
                "Exist commands:\r\n" +
                "connect       - connection to database\r\n" +
                "select        - query from table\r\n" +
                "tablelist     - get names all tables from data base\r\n" +
                "new_table     - create table in data base\r\n" +
                "update        - update rows in the table\r\n" +
                "insert        - insert new row in the table\r\n" +
                "delete        - delete row from table\r\n" +
                "drop_table    - delete table from data base\r\n" +
                "exit          - exit from application\r\n" +
                "get_columns   - get columns from table");
    }
}