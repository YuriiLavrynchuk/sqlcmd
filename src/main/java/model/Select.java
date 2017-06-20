package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Select {
    private final Statement statement;

    public Select(Statement statement){
        this.statement = statement;
    }

    public DataSet[] select(String tableName) {
        int size = getTableSize(tableName);
        DataSet[] result = new DataSet[size];
        try (ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName)){
            ResultSetMetaData statement = rs.getMetaData();
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

    public String[] getTableColumns(String tableName) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM information_schema.columns WHERE table_schema = 'public' AND table_name = '" + tableName + "'");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("column_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            statement.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}