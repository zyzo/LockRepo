package dha.lockrepo.core.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import dha.lockrepo.core.Constants;
import dha.lockrepo.core.enums.MessageEnum;

public class MessageAccessor {

    private static Locale DEFAULT_LOCALE = Locale.getDefault();

    /**
     * Return the message of the default locale indicated by the JVM
     */
    public static String getMessage(String message) {
        return MessageAccessor.getResource(message, DEFAULT_LOCALE);
    }

    /**
     * Return the corresponding message of the default locale indicated by the
     * JVM
     */
    public static String getMessage(MessageEnum messageEnum) {
        return MessageAccessor.getMessage(messageEnum, DEFAULT_LOCALE);
    }

    public static String getMessage(MessageEnum messageEnum, Locale locale) {
        return MessageAccessor.getResource(messageEnum.getKey(), DEFAULT_LOCALE);
    }

    private static String getResource(String message, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(Constants.MESSAGE_PROPERTIES_PATH, locale);
        try {
            return bundle.getString(message);
        } catch (MissingResourceException e) {
            return MessageAccessor.buildMissingMessage(message);
        }
    }

    private static String buildMissingMessage(String message) {
        return "??" + message + "??";
    }
}
