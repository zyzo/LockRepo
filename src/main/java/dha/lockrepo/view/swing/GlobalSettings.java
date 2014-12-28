package dha.lockrepo.view.swing;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class GlobalSettings {

    private static String[] commonForegroundColor = {
            "TextField.foreground",
    };

    void setColor() {
        for (String s : commonForegroundColor)
            UIManager.put(s, new Color(160, 40, 33));
    }

    private static final String[] commonFonts = {
            "Label.font",
            "TextField.font",
            "List.font",
            "PopupMenu.font",
            "TextArea.font",
            "MenuItem.font"
    };
    private static final FontUIResource buttonFont = new FontUIResource("NanumGothic",Font.BOLD, 12);
    void setDefaultFont() {

        FontUIResource commonFont = new FontUIResource("NanumGothic", Font.PLAIN, 12);
        for (String s : commonFonts) {
            UIManager.put(s, commonFont);
        }
        UIManager.put("Button.font", buttonFont);
    }

    void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus Look & Feel not available");
        }
    }
}
