package dha.lockrepo.core;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class LRUtils {
    public static final boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static final void copyToClipboard(String s) {
        StringSelection stringSelection = new StringSelection(s);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard ();
        clpbrd.setContents(stringSelection, null);
    }
}
