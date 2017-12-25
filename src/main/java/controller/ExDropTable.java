package controller;

/**
 * Класс комманды удаления таблицы "drop_table".
 * Проверяет вводимую комманду, считывает и выполняет sql-запрос.
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

public class ExDropTable implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private final InsertUpdateDeleteCreate crud;

    /**
     * Создаёт объект комманды "drop_table".
     * @param dataInOut объект ввода/вывода
     * @param dBconnection объект подключения к БД
     * @param crud объект выполняющий запросы к БД
     */
    public ExDropTable(DataInOut dataInOut, DbConnection dBconnection, InsertUpdateDeleteCreate crud) {
        this.dataInOut = dataInOut;
        this.dBconnection = dBconnection;
        this.crud = crud;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("drop_table");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tableName:");
        String tableName = dataInOut.inPut();
        String insertMsg = "DROP TABLE " + tableName;
        //Проверка и выполнение введённого запроса
        try (Statement statement = dBconnection.getStatement()){
                crud.run(statement, insertMsg);
                dataInOut.outPut("Table dropped");
        } catch (SQLException e){
            new InvalidException("ExDropTable ERROR", e);
        }
    }
}
