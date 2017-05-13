package model;

import exeption.InvalidException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
    private Statement statement;

    public Select(Statement statement){
        this.statement = statement;
    }

    public DataSet[] select(String tableName) {
        try {
            int size = getTableSize(tableName);
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
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
            rs.close();
            return result;
        } catch (SQLException e) {
//            e.printStackTrace();
            return new DataSet[0];
        }
    }

    private int getTableSize(String tableName){
        ResultSet selectCount = null;
        int tableSize = 0;
        try {
            selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
            selectCount.next();
            tableSize = selectCount.getInt(1);
            selectCount.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableSize;
    }
}