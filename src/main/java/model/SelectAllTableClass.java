package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SelectAllTableClass {

    public SelectAllTableClass(Statement statement) throws SQLException {
        ResultSet select = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' AND table_type = 'BASE TABLE'");
        String[] tables = new String[100];
        int index = 0;
        while (select.next()) {
//            System.out.println("Номер в выборке #" + select.getRow()
//                    + "\t" + select.getString("table_name"));
            tables[index++] = select.getString("table_name");
        }
        tables = Arrays.copyOf(tables, index + 1, String[].class);
//        select.close();
//        statement.close();
    }
}