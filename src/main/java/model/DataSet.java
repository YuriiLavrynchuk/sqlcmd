package model;

import java.util.Arrays;

public class DataSet {

    static class Data {
        private final String name;
        private final Object value;

        public Data(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    private final Data[] data = new Data[100]; // TODO remove magic number 100
    private int freeIndex = 0;

    public void put(String name, Object value) {
        data[freeIndex++] = new Data(name, value);
    }

    public Object[] getValues() {
        Object[] result = new Object[freeIndex];
        for (int i = 0; i < freeIndex; i++) {
            result[i] = data[i].getValue();
        }
        return result;
    }

    public String[] getNames() {
        String[] result = new String[freeIndex];
        for (int i = 0; i < freeIndex; i++) {
            result[i] = data[i].getName();
        }
        return result;
    }

    @Override
    public String toString() {
        return "DataSet{\n" +
                "names:" + Arrays.toString(getNames()) + "\n" +
                "values:" + Arrays.toString(getValues()) + "\n" +
                "}";
    }
}
