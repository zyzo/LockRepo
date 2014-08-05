package dha.lockrepo.utils;

import junit.framework.TestCase;
import dha.lockrepo.core.utils.MessageAccessor;

public class MessageAccessorTest extends TestCase {

    public void testDefaultMessageAccessorInvalidKey() {
        String actual = MessageAccessor.getMessage("whatever");
        String expected = "??whatever??";
        assertEquals(expected, actual);
    }
    
}
