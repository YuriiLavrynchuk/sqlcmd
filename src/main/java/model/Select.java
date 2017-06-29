package model;

import view.DataInOut;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Select {
    private final Statement statement;
    private DataInOut dataInOut;

    public Select(Statement statement, DataInOut dataInOut){
        this.statement = statement;
        this.dataInOut = dataInOut;
    }

    public DataSet[] select(String tableName) throws SQLException {
        int size = getTableSize(tableName);
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM " + tableName);

        ResultSetMetaData rsmd = rs.getMetaData();
        DataSet[] result = new DataSet[size];
        int index = 0;
        while (rs.next()) {
            DataSet dataSet = new DataSet();
            result[index++] = dataSet;
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dataSet.put(rsmd.getColumnName(i), rs.getObject(i));
            }
        }
//        rs.close();
//        statement.close();
        return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new DataSet[0];
        }
    }


    private int getTableSize(String tableName) throws SQLException {
        int tableSize = 0;
            ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
            selectCount.next();
            tableSize = selectCount.getInt(1);
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
//            statement.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}