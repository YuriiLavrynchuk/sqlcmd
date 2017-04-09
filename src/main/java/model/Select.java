package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Select {
    private Statement statement;

    public Select(Statement statement){
        this.statement = statement;
    }

    public void select(){
        try {
            String tableName = "users";
            int tableSize = getTableSize(tableName);

            ResultSet select = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = select.getMetaData();

            DataSet[] result = new DataSet[tableSize];
            int index = 0;
            while (select.next()) {
                DataSet dataSet = new DataSet();
                result[index++] = new DataSet();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    //
                    dataSet.put(rsmd.getColumnName(i), select.getObject(i));
                }
            }
            System.out.println(Arrays.toString(result));
        } catch (SQLException e){
            System.out.println("Select ERROR");
            e.printStackTrace();
        }
        try {
            statement.close();
            System.out.println("Select statement closed");
        } catch (SQLException e) {
            System.out.println("Select statement.close() ERROR");
            e.printStackTrace();
        }
    }

    //TODO Добавить try/catch
    private int getTableSize(String tableName) throws SQLException {
        ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
        selectCount.next();
        int tableSize = selectCount.getInt(1);
        selectCount.close();
        return tableSize;
    }
}