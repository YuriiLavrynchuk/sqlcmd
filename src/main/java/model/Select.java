package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Select {

    public DataSet[] select(String tableName, Statement statement) throws SQLException {
        int size = getTableSize(tableName, statement);
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY 1");
            ResultSetMetaData rsMd = rs.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()){
                DataSet dataSet = new DataSet();
                result[index++] = dataSet;
                for (int i = 1; i <= rsMd.getColumnCount(); i++){
                    dataSet.put(rsMd.getColumnName(i), rs.getObject(i));
                }
            }
            return result;
        } catch (SQLException e){
            return new DataSet[0];
        }
    }

//    //использование List
//    public List<String> select2(String tableName, Statement statement) throws SQLException {
//        int size = getTableSize(tableName, statement);
//
//        List<String> result = new ArrayList<>();
//        try {
//            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY 1");
//            ResultSetMetaData rsMd = rs.getMetaData();
//            while (rs.next()){
//                for (int i = 1; i <= rsMd.getColumnCount(); i++){
//                    result.add(rs.getString(i));
//                }
//                System.out.println(Arrays.toString(result.toArray()));
//            }
//            return result;
//        } catch (SQLException e){
//             result.add("nulllllllll");
//            return result;
//        }
//    }

    //использование HashMap
    public List<String> select2(String tableName, Statement statement) throws SQLException {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY 1");
            ResultSetMetaData rsMd = rs.getMetaData();
            List<String> result = new ArrayList();
            while (rs.next()){
                HashMap row = new HashMap(rsMd.getColumnCount());
                for (int i = 1; i <= rsMd.getColumnCount(); i++){
                    row.put(i,rs.getObject(i).toString());
                }
                result.add(row.values().toString());
//                result.add(new String[]{row.values().toString()});
            }
//            String[] str = new String[result.size()];
//            for (int j = 0; j <result.size() ; j++) {
//                str[j] = result.get(j).toString();
//            }
//            System.out.println(str[1]);
//            System.out.println(result.get(0));
            return result;
        } catch (SQLException e){
            return new ArrayList<>();
//            return new String[]{"errror"};
        }
    }

//    public void splitResult(String[] data){
//
//    }

    private int getTableSize(String tableName, Statement statement){
        int tableSize = 0;
        try{
            ResultSet selectCount = statement.executeQuery("SELECT count(*) FROM " + tableName);
            selectCount.next();
            tableSize = selectCount.getInt(1);
        } catch (SQLException e){
        }
        return tableSize;
    }

    public String[] getTableColumns(String msg, Statement statement){
        String tableName;
        int spaceIndex = msg.indexOf(" ");
        if(spaceIndex > 0){
            tableName = msg.substring(0, spaceIndex);
        }else {
            tableName = msg;
        }
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM information_schema.columns WHERE table_schema" +
                    " = 'public' AND table_name = '" + tableName + "'");

            List<String> listTable = new ArrayList<>();

            int index = 0;
            while (rs.next()){
                listTable.add(index++, rs.getString("column_name"));
            }
            return listTable.toArray(new String[0]);
        } catch (SQLException e){
            return new String[0];
        }
    }
}