package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

    Properties props = new Properties();

    public ConfigFileReader() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/test/resources/hudl/hudlconfig.properties");
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties getProps() {
        return props;
    }
}
