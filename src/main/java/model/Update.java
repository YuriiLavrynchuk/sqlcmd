package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public Update(Statement statement, String updateMsg) throws SQLException {
        try {
            statement.executeUpdate(updateMsg);
        } catch (SQLException e){
            throw e;
        }
    }

    public Update(Connection connection, String tableName, int id, DataSet newValue) throws SQLException {

        String tableNames = new GetNamesValuesFormated(newValue, "%s = ?,").GetNamesFormated();
        String sql = "UPDATE public." + tableName + " SET " + tableNames + " WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            int index = 1;
            for (Object value : newValue.getValues()){
                ps.setObject(index, value);
                index++;
            }
            ps.setInt(index, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw e;
        }
    }
}