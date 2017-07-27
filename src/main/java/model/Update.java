package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    private DataSet newValue;
    private int id;
    private Connection connection;
    private String tableName;

    public Update() {
    }

    public Update(Connection connection, String tableName, int id, DataSet newValue) throws SQLException {
        this.connection = connection;
        this.tableName = tableName;
        this.id = id;
        this.newValue = newValue;
        updateRunWithParameters();
    }

    public void updateRun(Statement statement, String updateMsg) throws SQLException {
        try {
            statement.executeUpdate(updateMsg);
        } catch (SQLException e){
            throw e;
        }
    }

    public void updateRunWithParameters() throws SQLException {
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