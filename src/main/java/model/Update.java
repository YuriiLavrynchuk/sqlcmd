package model;

import exeption.InvalidException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public Update(Statement statement, String updatemsg) throws SQLException {
        try {
            statement.executeUpdate(updatemsg);
            System.out.println("Row updated");
        } catch (SQLException e) {
            new InvalidException("Update ERROR", e);
        }
    }

    public Update(Connection connection, String tablename, int id, DataSet newValue) throws SQLException {

        String tableNames = new GetNamesValuesFormated(newValue, "%s = ?,").GetNamesFormated();
        String sql = "UPDATE public." + tablename + " SET " + tableNames + " WHERE id = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            int index = 1;
            for (Object value : newValue.getValues()) {
                ps.setObject(index, value);
                index++;
            }
            ps.setInt(index, id);
            ps.executeUpdate();
        } catch (SQLException e){
            //do nothing
        }
    }
}