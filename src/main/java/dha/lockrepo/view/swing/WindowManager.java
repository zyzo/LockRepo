package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretPieceVO;

import javax.swing.*;

public class WindowManager {

    private JFrame mainWindowFrame;
    private MainWindow mainWindow;
    private JFrame infoWindowFrame;
    private InfoWindow infoWindow;
    private JFrame confirmDeleteWindowFrame;
    private TopSecretPieceVO selectedItem;
    private JFrame addWindowFrame;

    public WindowManager() {
        // open main window
        mainWindowFrame = createMainWindowFrame("LockRepo");
        infoWindowFrame = createInfoWindowFrame(null);
        confirmDeleteWindowFrame = createConfirmDeleteWindowFrame("Confirmation");
        addWindowFrame = createAddWindowFrame("Create new secret");
    }

    void openInfoWindow(TopSecretPieceBE item) {
        updateInfoWindow(item);
        infoWindowFrame.setVisible(true);
    }

    private void updateInfoWindow(TopSecretPieceBE item) {
        infoWindow.setItem(item);
        infoWindowFrame.setTitle(item.getTitle());
    }


    void openConfirmDeleteWindow(TopSecretPieceVO topSecretPieceVO) {
        this.selectedItem = topSecretPieceVO;
        confirmDeleteWindowFrame.setVisible(true);
    }

    void openAddWindow() {
        this.addWindowFrame.setVisible(true);
    }

    void deleteConfirmed() {
        confirmDeleteWindowFrame.setVisible(false);
        mainWindow.deleteItem(this.selectedItem);
        mainWindowFrame.pack();
    }

    void deleteCanceled() {
        confirmDeleteWindowFrame.setVisible(false);
    }

    void closeInfoWindow(TopSecretPieceBE item) {
        infoWindowFrame.setVisible(false);
    }
    private JFrame createMainWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        mainWindow = new MainWindow(this);
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

    private JFrame createConfirmDeleteWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new ConfirmDeleteWindow(this).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return  frame;
    }

    private JFrame createAddWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new AddPieceWindow(this).mainPanel);
        frame.pack();
        return  frame;
    }

}
