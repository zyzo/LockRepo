package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;

public class WindowManager {

    private JFrame mainWindowFrame;
    private JFrame infoWindowFrame;
    private InfoWindow infoWindow;

    public WindowManager() {
        // open main window
        mainWindowFrame = createMainWindowFrame("LockRepo");
        infoWindowFrame = createInfoWindowFrame(null);
    }

    void openInfoWindow(TopSecretPieceBE item) {
        System.out.println(item.hashCode());
        if (infoWindowFrame == null) {
            infoWindowFrame = createInfoWindowFrame(item.getTitle());
        }
        updateInfoWindow(item);
        infoWindowFrame.setVisible(true);
    }

    private void updateInfoWindow(TopSecretPieceBE item) {
        infoWindow.setItem(item);
        infoWindowFrame.setTitle(item.getTitle());
    }

    void closeInfoWindow(TopSecretPieceBE item) {
        infoWindowFrame.setVisible(false);
    }
    private JFrame createMainWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        MainWindow mainWindow = new MainWindow(this);
        JPanel contentPane = mainWindow.getMainPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(contentPane);
        frame.pack();
        return frame;
    }

    private JFrame createInfoWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        infoWindow = new InfoWindow(this);
        JPanel contentPane = infoWindow.getMainPanel();
        frame.setContentPane(contentPane);
        frame.pack();
        return frame;
    }


}
