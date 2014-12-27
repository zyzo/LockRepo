package dha.lockrepo.view.swing;

import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.domains.TopSecretBE;
import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;

public class WindowManager {

    private JFrame mainWindowFrame;
    private JFrame infoWindowFrame;
    private MainWindow mainWindow;
    private InfoWindow infoWindow;

    private TopSecretService topSecretService;
    public enum WindowType {
        MAIN,
        INFO
    }

    public WindowManager() {
        topSecretService = new TopSecretServiceImpl();
        mainWindowFrame = createJFrame("LockRepo", WindowType.MAIN);
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setVisible(true);
    }

    void openInfoWindow(String selectedItem) {
        infoWindowFrame = createJFrame(selectedItem, WindowType.INFO);
        TopSecretPieceBE item = topSecretService.findPieceById(90L);
        infoWindow.setItem(item);
        infoWindowFrame.setVisible(true);
    }

    private JFrame createJFrame(String title, WindowType window) {
        JFrame frame = new JFrame(title);
        JPanel contentPane = null;
        switch (window) {
        case MAIN:
            mainWindow = new MainWindow(this);
            contentPane = mainWindow.getMainPanel();
            break;
        case INFO:
            infoWindow = new InfoWindow();
            contentPane = infoWindow.getMainPanel();
            break;
        }
        frame.setContentPane(contentPane);
        frame.pack();
        return frame;
    }
}
