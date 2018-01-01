package model;

/**
 * Класс выборки из БД.
 * Предназначен для непосредственного вытаскиваения данных из БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Select {

    /**
     * Метод передаёт запрос в БД и получает объект с данными.
     * @param tableName
     * @param statement
     * @return озращает объект типа List с данными из таблицы.
     * @throws SQLException
     */
    public List<String> select(String tableName, Statement statement) throws SQLException {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY 1");
            ResultSetMetaData rsMd = rs.getMetaData();
            List<String> result = new ArrayList();
            while (rs.next()){
                List<String>  row = new ArrayList<>();
                for (int i = 1; i <= rsMd.getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                result.add(row.toString().substring(1, row.toString().length()-1));
            }
            return result;
        } catch (SQLException e){
            return new ArrayList<>();
        }
    }

    /**
     * Метод возвращает размер таблицы
     * @param tableName
     * @param statement
     * @return возвращает объект типа int с размером таблицы
     */
    private int getTableSize(String tableName, Statement statement){
        int tableSize = 0;
        try{
            ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
            selectCount.next();
            tableSize = selectCount.getInt(1);
        } catch (SQLException e){
        }
        return tableSize;
    }

    /**
     * Метод возвращает наименование столбцов из таблицы
     * @param msg
     * @param statement
     * @return возвращает объект типа List
     */
    public String[] getTableColumns(String msg, Statement statement){
        String tableName;
        int spaceIndex = msg.indexOf(" ");
        if(spaceIndex > 0){
            tableName = msg.substring(0, spaceIndex);
        }else {
            tableName = msg;
        }
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM information_schema.columns WHERE table_schema" +
                    " = 'public' AND table_name = '" + tableName + "'");

            List<String> listTable = new ArrayList<>();

            int index = 0;
            while (rs.next()){
                listTable.add(index++, rs.getString("column_name"));
            }
            return listTable.toArray(new String[0]);
        } catch (SQLException e){
            return new String[0];
        }
    }
}