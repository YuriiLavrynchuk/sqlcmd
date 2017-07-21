package model;

import view.DataInOut;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Select {
    private final Statement statement;

    public Select(Statement statement, DataInOut dataInOut){
        this.statement = statement;
    }

    public DataSet[] select(String tableName) throws SQLException {
        int size = getTableSize(tableName);
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsMd = rs.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()){
                DataSet dataSet = new DataSet();
                result[index++] = dataSet;
                for (int i = 1; i <= rsMd.getColumnCount(); i++){
                    dataSet.put(rsMd.getColumnName(i), rs.getObject(i));
                }
            }
            return result;
        } catch (SQLException e){
            return new DataSet[0];
        }
    }

    private int getTableSize(String tableName){
        int tableSize = 0;
        try{
            ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
            selectCount.next();
            tableSize = selectCount.getInt(1);
        } catch (SQLException e){
        }
        return tableSize;
    }

    public String[] getTableColumns(String msg){
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