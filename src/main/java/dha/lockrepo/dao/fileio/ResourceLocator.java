package dha.lockrepo.dao.fileio;

import java.io.File;

public class ResourceLocator {

    /**
     * Return the directory path of the current application
     */
    public static String getDirectoryPath() {
        try {
            return new File("").getAbsolutePath();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }
}
