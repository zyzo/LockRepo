package dha.lockrepo.view;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.Test;

import dha.lockrepo.core.exceptions.AccessRightException;
import dha.lockrepo.view.ResourceLocator;

public class ResourceLocatorTest {

    @Test
    public void testGetDirectoryPath() throws AccessRightException, MalformedURLException {
        String expected = "/home/zyzo/Dropbox/Projects/LockRepo";
        String actual = ResourceLocator.getDirectoryPath();
        assertEquals(expected, actual);
    }
}
