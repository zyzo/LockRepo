package dha.lockrepo.view.swing;

public class MainEntry {

    public static void main(String[] args) {
        GlobalSettings g = new GlobalSettings();
        g.setLookAndFeel();
        g.setDefaultFont();
        g.setColor();
        WindowManager manager = new WindowManager();
        manager.start();
    }

}
