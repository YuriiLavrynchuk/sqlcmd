package integration;

import view.DataInOut;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurableInputStream extends InputStream implements DataInOut {
    private String line;

    @Override
    public int read() throws IOException {
        if (line.length() == 0) {
            return -1;
        }
        char ch = line.charAt(0);
        line = line.substring(1);

        return 1;
    }

    public void add(String line){
        if(this.line == null) {
            this.line = line;
        } else {
            this.line += "\n" + line;
        }
    }

    public String getLine(){
        add(line);
        return line;
    }

    @Override
    public void outPut(String message) {

    }

    @Override
    public String inPut() {
//        Scanner scanner = new Scanner(System.in);

        return line;
    }
}
