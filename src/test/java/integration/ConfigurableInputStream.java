package integration;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurableInputStream extends InputStream{
    private String line;

    @Override
    public int read() throws IOException {
//        char ch = line.indexOf(0);
        line = line.substring(1);

        return 1;
    }
}
