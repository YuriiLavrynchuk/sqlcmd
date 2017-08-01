package controller;

import dnl.utils.text.table.TextTable;

import java.util.LinkedList;
import java.util.List;

public class PrintTable {
    public static void main(String[] args) {

        String[] columns = new String[]{"column1", "column1", "column1"};
        List<String> list = new LinkedList<>();
//        table[0] = new String[] { "foo", "bar", "baz" };
//        table[1] = new String[] { "bar2 foo baz2" };
//        table[2] = new String[] { "baz3 bar3 foo3" };
//        table[3] = new String[] { "foo4 bar4 baz4" };

//        list.add("foo bar baz");
//        list.add("111 222 333");
//        list.add("000 000 000");
//        list.add("999 888 777");

        list.add( "foo, bar, baz" );
        list.add("111 222 333");
        list.add("000 000 000");
        list.add("999 888 777");

        Object[][] table = new Object[4][];
        for (int i = 0; i < list.size(); i++) {
            table[i] = new String[]{list.get(i)};
        }

//        Object[][] table = new Object[4][];
//        table[0] = new String[] { "foo", "bar", "baz" };
//        table[1] = new String[] { "bar2 foo baz2" };
//        table[2] = new String[] { "baz3 bar3 foo3" };
//        table[3] = new String[] { "foo4 bar4 baz4" };


//        String[] columns = new String[]{"column1", "column2"};
//        Object [][] table = new Integer[2][2];
//        table[0][0] = 1;
//        table[0][1] = 1;
//        table[1][0] = 1;
//        table[1][1] = 1;

//        System.out.println(Arrays.toString(list.toArray()));

        TextTable tt = new TextTable(columns, table);
//        tt.printTable();
    }

//    public String[] extractValue (String x){
//        for (int i = 0; i < x.length() ; i++) {
//
//        }
//
//        return null;
//    }
}
