package dha.lockrepo.dao.fileio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import dha.lockrepo.core.enums.MessageEnum;
import dha.lockrepo.dao.FileConstants;

public class ResourceLoader {

    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    /**
     * Return the message of the default locale indicated by the JVM
     * 
     */
    public static String getMessage(String message) {
        return ResourceLoader.getResource(message, DEFAULT_LOCALE);
    }

    /**
     * Return the corresponding message of the default locale indicated by the
     * JVM
     */
    public static String getMessage(MessageEnum messageEnum) {
        return ResourceLoader.getMessage(messageEnum, DEFAULT_LOCALE);
    }

    public static String getMessage(MessageEnum messageEnum, Locale locale) {
        return ResourceLoader.getResource(messageEnum.getKey(), locale);
    }

    private static String getResource(String message, Locale locale) {
        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = buildPropertyPathFromLocale(locale);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            properties.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String resource = properties.getProperty(message);
        if (resource != null) {
            return resource;
        } else {
            return buildMissingMessage(message);
        }
    }

    private static FileInputStream buildPropertyPathFromLocale(Locale locale) throws FileNotFoundException {
        String path = FileConstants.MESSAGE_PROPERTIES_PATH
                + FileConstants.PROPERTY_FILE
                + "_" + locale.getLanguage()
                + "_" + locale.getCountry()
                + ".properties";
        String defaultPath = FileConstants.MESSAGE_PROPERTIES_PATH
                + FileConstants.PROPERTY_FILE
                + ".properties";
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            return new FileInputStream(defaultPath);
        }
    }
    private static String buildMissingMessage(String message) {
        return "??" + message + "??";
    }
}
