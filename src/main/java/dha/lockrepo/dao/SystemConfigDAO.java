package dha.lockrepo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfigDAO {
    private Properties props;

    public SystemConfigDAO() {
        props = new Properties();
        InputStream s = this.getClass().getClassLoader().getResourceAsStream(FileConstants.FILE_SYSTEM_CONFIG);
        try {
            props.load(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getProperty(String key) {
        return props.get(key);
    }
}
