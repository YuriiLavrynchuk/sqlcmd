package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
    private final Statement statement;

    public Select(Statement statement){
        this.statement = statement;
    }

    public DataSet[] select(String tableName) throws SQLException {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName)){
            int size = getTableSize(tableName);
            ResultSetMetaData statement = rs.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()) {
                DataSet dataSet = new DataSet();
                result[index++] = dataSet;
                for (int i = 1; i <= statement.getColumnCount(); i++) {
                    dataSet.put(statement.getColumnName(i), rs.getObject(i));
                }
            }
            return result;
        } catch (SQLException e) {
//            e.printStackTrace();
            return new DataSet[0];
        }
    }

    private int getTableSize(String tableName){
        int tableSize = 0;
        try (ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName)){
            selectCount.next();
            tableSize = selectCount.getInt(1);
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return tableSize;
    }
}