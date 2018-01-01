package model;

/**
 * Класс списка таблиц в БД.
 * Предназначен для отображения списка таблиц в БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectTablesList {

    /**
     * Метод непосредственно выводит скисок таблиц из БД.
     * @param statement
     * @return возвращает масив String с именами таблиц.
     */
    public String[] selectAllTable(Statement statement){
        try {
            ResultSet select = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE " +
                    "table_schema = 'public' AND table_type = 'BASE TABLE'");

            List<String> listTables = new ArrayList<>();

            int index = 0;
            while (select.next()){
                listTables.add(index++, select.getString("table_name"));
            }
            return listTables.toArray(new String[0]);
        } catch (SQLException e){
        }
        return new String[0];
    }
}