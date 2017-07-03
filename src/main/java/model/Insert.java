package model;

import exeption.InvalidException;

import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public Insert(Statement statement, String insertmsg) throws SQLException {
        try {
            statement.executeUpdate(insertmsg);
            System.out.println("Row inserted");
        } catch (SQLException e) {
            new InvalidException("ExInsert insert ERROR", e);
        }
    }

    public Insert(Statement st, String tablename, DataSet input) throws SQLException {
        try {
            String tableNames = new GetNamesValuesFormated(input, "%s,").GetNamesFormated();
            String values = new GetNamesValuesFormated(input, "'%s',").getValuesFormated();

            st.executeUpdate("INSERT INTO " + tablename + " (" + tableNames + ")" +
                    "VALUES (" + values + ")");
            st.close();
        } catch (SQLException e) {
            //do nothing
        }
    }
}