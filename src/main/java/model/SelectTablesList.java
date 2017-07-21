package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectTablesList {
    private final Statement statement;

    public SelectTablesList(Statement st){
         this.statement = st;
    }

    public String[] selectAllTable(){
        try {
            ResultSet select = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE " +
                    "table_schema = 'public' AND table_type = 'BASE TABLE'");

            List<String> listTables = new ArrayList<>();

            int index = 0;
            while (select.next()){
                listTables.add(index++, select.getString("table_name"));
            }
            return listTables.toArray(new String[0]);
        } catch (SQLException e){
        }
        return new String[0];
    }
}