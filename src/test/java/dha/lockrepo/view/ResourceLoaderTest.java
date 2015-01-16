package dha.lockrepo.view;

import dha.lockrepo.core.enums.MessageEnum;
import junit.framework.TestCase;

import java.util.Locale;

public class ResourceLoaderTest extends TestCase {

    public void testMessageInvalidKey() {
        String actual = ResourceLoader.getMessage("whatever");
        String expected = "??whatever??";
        assertEquals(expected, actual);
    }
    
    public void testMessageValidKey() {
        String key = "test.message.DO_NOT_DELETE_OR_MODIFY";
        String actual = ResourceLoader.getMessage(key);
        String expected = "It worked!!";
        assertEquals(expected, actual);
    }

    public void testMessageEnum() {
        String actual = ResourceLoader.getMessage(MessageEnum.TEST_RESOURCE_LOADER);
        String expected = "It worked!!";
        assertEquals(expected, actual);
    }

    public void testMessageEnumWithLocale() {
        String actual = ResourceLoader.getMessage(MessageEnum.TEST_RESOURCE_LOADER, new Locale("vi", "vn"));
        String expected = "It worked!! In Vietnam!!";
        assertEquals(expected, actual);
    }

}
