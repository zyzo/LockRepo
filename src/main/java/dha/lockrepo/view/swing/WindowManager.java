package dha.lockrepo.view.swing;

import javax.swing.*;

public class WindowManager {

    public enum WindowType {
        MAIN,
        INFO
    }

    public WindowManager() {
        createJFrame("LockRepo", WindowType.MAIN);
    }

    void openInfoWindow(String selectedItem) {
        createJFrame(selectedItem, WindowType.INFO);
    }

    private void createJFrame(String title, WindowType window) {
        JFrame frame = new JFrame(title);
        JPanel contentPane = null;
        switch (window) {
        case MAIN:
            contentPane = new MainWindow(this).getMainPanel();
            break;
        case INFO:
            contentPane = new InfoWindow().getMainPanel();
            break;
        }
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
