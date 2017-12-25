package controller;

/**
 * Класс комманды "select".
 * Проверяет вводимую комманду, считывает и выполняет sql-запрос выборки из таблицы.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import dnl.utils.text.table.TextTable;
import exeption.InvalidException;
import model.DataSet;
import model.DbConnection;
import model.Select;
import view.DataInOut;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExSelect implements Command {
    private final DataInOut dataInOut;
    private final DbConnection dBconnection;
    private Select select;

    /**
     * Создаёт объект комманды "select".
     * @param dataInOut объект ввода/вывода
     * @param dbConnection объект подключения к БД
     * @param select объект выполняющий выборку из таблицы.
     */
    public ExSelect(DataInOut dataInOut, DbConnection dbConnection, Select select) {
        this.dataInOut = dataInOut;
        this.dBconnection = dbConnection;
        this.select = select;
    }

    @Override
    public boolean checkCommand(String command){
        return command.equals("select");
    }

    @Override
    public void execute(String command){
        dataInOut.outPut("Enter tableName:");
        String selectMsg = dataInOut.inPut();
        ////Проверка и выполнение введённого запроса
        try (Statement statement = dBconnection.getStatement()){
            //возвращает массив столбцов таблицы
            String[] tableColumns = select.getTableColumns(selectMsg, statement);
            //возвращает массив строк таблицы
            List<String> tableData = select.select(selectMsg, statement);
            //печать таблицы
            printerTables(tableColumns, getFields(tableData));
        } catch (SQLException e){
            new InvalidException("ExSelect select Error", e);
        }
    }

    /**
     * Метод преобразовует полученный из БД List tableData в двумерный массив для передачи в принтер таблицы
     * @param listdata
     * @return array
     */
    public String[][] getFields(List<String> listdata) {
        String[][] array = new String[listdata.size()][];
        int index = 0;
        for (String row: listdata) {
            array[index] = listdata.get(index).split(", ");
            index++;
        }
        return array;
    }

    /**
     * Актуальный метод для печати таблицы
     * @param columns
     * @param data
     */
    public void printerTables (String[] columns, String[][] data){
        TextTable textTable = new TextTable(columns, data);
        textTable.printTable();
    }

    /**
     * Метод для печпати таблицы (устаревший)
     * @deprecated
     * @param tableData
     */
    public void printTable(DataSet[] tableData){
        for (DataSet row : tableData){
            printRow(row);
        }
        dataInOut.outPut("--------------------");
    }

    /**
     * Метод для печпати строк таблицы (устаревший)
     * @deprecated
     * @param row
     */
    public void printRow(DataSet row){
        Object[] values = row.getValues();

        StringBuilder result = new StringBuilder("|");
        for (Object value : values) {
            result.append(value).append("|");
        }
        dataInOut.outPut(result.toString());
    }

    /**
     * Метод для печпати заголовка таблицы (устаревший)
     * @deprecated
     * @param tableColumns
     */
    public void printHeader(String[] tableColumns){

        StringBuilder result = new StringBuilder("|");
        for (String name : tableColumns){
            result.append(name).append("|");
        }
        dataInOut.outPut("--------------------");
        dataInOut.outPut(result.toString());
        dataInOut.outPut("--------------------");
    }
}
