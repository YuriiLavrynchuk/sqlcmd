package model;

class GetNamesValuesFormated {

    private final String format;
    private final DataSet newValue;

    public GetNamesValuesFormated(DataSet newValue, String format){
        this.newValue = newValue;
        this.format = format;
    }

    public String GetNamesFormated(){
        StringBuilder string = new StringBuilder("");
        for (String name : newValue.getNames()){
            string.append(String.format(format, name));
        }
        string = string.delete(string.length()-1, string.length());
        return string.toString();
    }


    public String getValuesFormated(){
        StringBuilder values = new StringBuilder("");
        for (Object value: newValue.getValues()){
            values.append(String.format(format, value));
        }
        values = values.delete(values.length()-1, values.length());
        return values.toString();
    }
}
