package dha.lockrepo.entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LockRepoEntry {

    private File securePieces, secureGroup;

    public void init() {
        securePieces = new File("sp");
        secureGroup = new File("sg");
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        LockRepoEntry entry = new LockRepoEntry();
        entry.init();
        entry.securePieces = new File("sp");
        PrintWriter writer = new PrintWriter("alo.txt", "UTF-8");
        writer.write("ADSASD");

    }
}
