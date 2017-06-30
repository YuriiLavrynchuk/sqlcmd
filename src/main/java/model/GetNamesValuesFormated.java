package model;

public class GetNamesValuesFormated {

    private String format;
    private DataSet newValue;

    public GetNamesValuesFormated(DataSet newValue, String format) {
        this.newValue = newValue;
        this.format = format;
    }

    public String GetNamesFormated(){
        String string = "";
        for (String name : newValue.getNames()) {
            string += String.format(format, name);
        }
        string = string.substring(0, string.length() - 1);
        return string;
    }


    public String getValuesFormated() {
        String values = "";
        for (Object value: newValue.getValues()) {
            values += String.format(format, value);
        }
        values = values.substring(0, values.length() - 1);
        return values;
    }
}
