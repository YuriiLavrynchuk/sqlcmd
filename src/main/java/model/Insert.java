package model;

import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public Insert(Statement statement, String insertMsg) throws SQLException {
        try {
            statement.executeUpdate(insertMsg);
            System.out.println("Row inserted");
        } catch (SQLException e){
            throw e;
        }
    }

    public Insert(Statement st, String tableName, DataSet input) throws SQLException {
        try {
            String tableNames = new GetNamesValuesFormated(input, "%s,").GetNamesFormated();
            String values = new GetNamesValuesFormated(input, "'%s',").getValuesFormated();

            st.executeUpdate("INSERT INTO " + tableName + " (" + tableNames + ")" +
                    "VALUES (" + values + ")");
            st.close();
        } catch (SQLException e){
            throw e;
        }
    }
}