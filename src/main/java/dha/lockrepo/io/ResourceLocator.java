package dha.lockrepo.io;

import java.io.File;
import java.net.MalformedURLException;

import dha.lockrepo.core.enums.MessageEnum;
import dha.lockrepo.core.exceptions.AccessRightException;

public class ResourceLocator {

    /**
     * Return the directory path of the current application
     */
    public static String getDirectoryPath() throws AccessRightException, MalformedURLException {
        try {
            return new File("").getAbsolutePath();
        } catch (SecurityException e) {
            throw new AccessRightException(
                    ResourceLoader.getMessage(MessageEnum.EXC_ACCESS_RIGHT_SEC));
        }
    }
}
