package dha.lockrepo.core.utils;

import java.io.File;

import dha.lockrepo.core.enums.MessageEnum;
import dha.lockrepo.core.exceptions.AccessRightException;

public class ResourceLocator {

    /**
     * Return the directory path of the current application
     */
    public static String getDirectoryPath() throws AccessRightException {
        try {
            return new File(".").getAbsolutePath();
        } catch (SecurityException e) {
            throw new AccessRightException(
                    MessageAccessor.getMessage(MessageEnum.EXC_ACCESS_RIGHT_SEC));
        }
    }
}
