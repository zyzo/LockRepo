package dha.lockrepo.dao.io;

import java.util.Locale;

import junit.framework.TestCase;
import dha.lockrepo.core.enums.MessageEnum;
import dha.lockrepo.dao.fileio.ResourceLoader;

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
