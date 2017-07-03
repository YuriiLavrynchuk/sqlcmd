package model;

import view.DataInOut;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Select {
    private final Statement statement;

    public Select(Statement statement, DataInOut dataInOut){
        this.statement = statement;
    }

    public DataSet[] select(String tableName) throws SQLException {
        int size = getTableSize(tableName);
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
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
        return result;
        } catch (SQLException e) {
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

    public String[] getTableColumns(String msg) throws SQLException{
        String tableName;
        int spaceindex = msg.indexOf(" ");
        if(spaceindex > 0){
            tableName = msg.substring(0, spaceindex);
        }else {
            tableName = msg;
        }
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
            return new String[0];
        }
    }
}