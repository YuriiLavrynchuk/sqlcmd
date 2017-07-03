package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SelectTablesList {
    private final Statement statement;

    public SelectTablesList(Statement st) {
         this.statement = st;
    }

    public String[] selectAllTable() throws SQLException{
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
            //do nothing
        }
        return new String[0];
    }
}