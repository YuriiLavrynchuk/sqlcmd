package model;

/**
 * Класс для изменения данных в БД.
 * Предназначен для непосредственного вытаскиваения данных из БД.
 *
 * @version 1.0.0
 *
 * @author Yuriy.Lavrinchuk
 *
 */
import java.sql.SQLException;
import java.sql.Statement;

public class InsertUpdateDeleteCreate {

    /**
     * Метод непосредственно передаёт запрос в БД для изменения данных.
     * @param statement
     * @param updateMsg
     * @throws SQLException
     */
    public void run(Statement statement, String updateMsg) throws SQLException {
        try {
            statement.executeUpdate(updateMsg);
        } catch (SQLException e){
            throw e;
        }
    }
}
