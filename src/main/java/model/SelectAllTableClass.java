package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SelectAllTableClass {
    private Statement statement;
    public SelectAllTableClass(Statement st) {
         this.statement = st;
    }

    public String[] SelectAllTable() {
        try {
            ResultSet select = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");
            String[] tables = new String[100];
            int index = 0;
            while (select.next()) {
                tables[index++] = select.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            return tables;
        } catch (SQLException e){
            e.printStackTrace();
            return new String[0];
        }
//        select.close();
//        statement.close();
    }
}