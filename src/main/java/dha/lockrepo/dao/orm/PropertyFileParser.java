package dha.lockrepo.dao.orm;

import dha.lockrepo.core.domains.LRProperty;
import dha.lockrepo.dao.fileio.DataGuardian;


public class PropertyFileParser {

    private static final String EQUAL_CHAR = "=";
    private DataGuardian dataGuardian;

    public PropertyFileParser(DataGuardian dataGuardian) {
        this.dataGuardian = dataGuardian;
    }

    public String getProperty(String key) {
        String line = " ";
        String property = null;
        dataGuardian.readBegin();
        while (line != null) {
            line = dataGuardian.readLine();
            if (line == null) {
                break;
            } else if (line.trim() != "") {
                LRProperty prop = getLRProperty(line);
                if (prop.getKey().equals(key)) {
                    property = prop.getValue();
                    break;
                }
            }
        }
        dataGuardian.readClose();
        return property;
    }
    /**
     * 
     * @param line
     * @return a LRProperty object which contains the pair <key,value>
     */
    private static LRProperty getLRProperty(String line) {
        String[] tokens = line.split(EQUAL_CHAR);
        if (tokens.length != 2) {
            throw new RuntimeException("Parse property file error");
        }
        return new LRProperty(tokens[0].trim(), tokens[1].trim());
    }

    public void setProperty(String key, String value) {
        int cntLine = 0;
        dataGuardian.readBegin();
        String line;
        while ((line = dataGuardian.readLine()) != null) {
            if (getLRProperty(line).getKey().equals(key)) {
                break;
            }
            cntLine++;
        }
        dataGuardian.readClose();
        if (line != null) {
            dataGuardian.removeLine(cntLine);
        }
        dataGuardian.writeLine(key + " " + EQUAL_CHAR + " " + value);
    }
}
