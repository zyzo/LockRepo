package dha.lockrepo.dao.io;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import dha.lockrepo.dao.fileio.DataGuardian;


public class DataGuardianTest {

    DataGuardian dg = DataGuardian.getInstance("T_PIECE.df");

    @BeforeClass
    public static void init() throws IOException {
        // delete file content
        FileWriter f = new FileWriter("T_PIECE.df");
        f.write("");
        f.close();
    }
    
    @Test
    public void testWrite() {
        dg.writeLine("abc");
        dg.writeLine("def");
    }

    @Test
    public void testRead() {
        dg.readBegin();
        try {
            assertTrue("Read failed", "abc".equals(dg.readLine()));
            assertTrue("Read failed", "def".equals(dg.readLine()));
        } finally {
            dg.readClose();
        }
    }

    @Test
    public void testReaderState() {
        dg.readBegin();
        for ( int i = 0; i < 10; i++ )
            dg.readLine();
        dg.readClose();
        dg.readBegin();
        String s = dg.readLine();
        assertTrue("Read failed : " + s, "abc".equals(s));
        dg.readClose();
    }

    @Test
    public void testRemove() {
        dg.writeLine("chj");
        dg.removeLine(1);
        dg.writeLine("afterthat");
    }

}
