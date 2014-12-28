package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretPieceVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
        addGlobalKeyEvents();
    }

    private void addGlobalKeyEvents() {
        //Hijack the keyboard manager
        KeyboardFocusManager manager =
                KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( new KeyDispatcher());

    }

    //Custom dispatcher
    class KeyDispatcher implements KeyEventDispatcher {

        public static final int KEY_CHAR_ESCAPE = 27;

        public boolean dispatchKeyEvent(KeyEvent e) {
            if(e.getID() == KeyEvent.KEY_TYPED) {
                if (mainWindowFrame.isActive()) {
                    if (e.getKeyChar() == 27) {
                        System.out.println("Quitting...");
                        System.exit(0);
                    }
                } else if (infoWindowFrame.isActive()) {
                    if (e.getKeyChar() == KEY_CHAR_ESCAPE) {
                        closeInfoWindow(selectedItem);
                    }
                } else if (addWindowFrame.isActive()) {
                    if (e.getKeyChar() == KEY_CHAR_ESCAPE) {
                        closeAddWindow();
                    }
                }
            }
            //Allow the event to be redispatched
            return false;
        }
    }

    void openInfoWindow(TopSecretPieceBE item) {
        infoWindow.setItem(item);
        infoWindowFrame.setTitle(item.getTitle());
        infoWindowFrame.setVisible(true);
    }

    void packInfoWindow() {
        infoWindowFrame.pack();
    }

    void update() {
        mainWindow.updateModel();
    }


    void openConfirmDeleteWindow(TopSecretPieceVO topSecretPieceVO) {
        this.selectedItem = topSecretPieceVO;
        confirmDeleteWindowFrame.setVisible(true);
    }

    void openAddWindow() {
        this.addWindowFrame.setVisible(true);
    }

    void packAddWindow() {
        this.addWindowFrame.pack();

    }
    public void closeAddWindow() {
        this.addWindowFrame.setVisible(false);
        mainWindow.updateModel();
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
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        positionOnCenter(frame);
        frame.setVisible(true);
        return frame;
    }

    private JFrame createInfoWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        infoWindow = new InfoWindow(this);
        JPanel contentPane = infoWindow.getMainPanel();
        frame.setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height - mainWindowFrame.getSize().height / 2);
        return frame;
    }

    private JFrame createConfirmDeleteWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new ConfirmDeleteWindow(this).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(mainWindowFrame);
        frame.pack();
        positionOnCenter(frame);
        return frame;
    }

    private void positionOnCenter(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private JFrame createAddWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new AddPieceWindow(this).mainPanel);
        frame.pack();
        frame.setLocation(mainWindowFrame.getX() + mainWindowFrame.getSize().width,
                mainWindowFrame.getY());
        return frame;
    }

}
