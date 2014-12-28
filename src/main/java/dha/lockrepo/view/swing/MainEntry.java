package dha.lockrepo.view.swing;

import javax.swing.*;

public class MainEntry {

    public static void main(String[] args) {
        setLookAndFeel();
        WindowManager manager = new WindowManager();
    }

    private static void setLookAndFeel() {
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
