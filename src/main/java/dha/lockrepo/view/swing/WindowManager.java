package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretPieceVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowManager {

    private JFrame mainWindowFrame;
    private MainWindow mainWindow;
    private JFrame infoWindowFrame;
    private InfoWindow infoWindow;
    private JFrame confirmDeleteWindowFrame;
    private TopSecretPieceVO selectedItem;
    private JFrame confirmQuitWindowFrame;
    private JFrame addWindowFrame;

    public WindowManager() {
        // open main window
        mainWindowFrame = createMainWindowFrame("LockRepo");
        infoWindowFrame = createInfoWindowFrame(null);
        confirmDeleteWindowFrame = createConfirmDeleteWindowFrame("Confirmation");
        addWindowFrame = createAddWindowFrame("Create new secret");
        addGlobalKeyEvents();
    }

    public void start() {
        mainWindowFrame.setVisible(true);
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
                        onCloseOperation();
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

    private void onCloseOperation() {
        if (infoWindowFrame.isVisible()) {
            closeInfoWindow(selectedItem);
        } else if (addWindowFrame.isVisible()) {
            closeAddWindow();
        } else {
            System.out.println("Quitting...");
            confirmQuitWindowFrame = createConfirmQuitWindowFrame("Confirmation");
            confirmQuitWindowFrame.setVisible(true);
        }
    }
    void openInfoWindow(TopSecretPieceBE item) {
        infoWindow.setItem(item);
        infoWindowFrame.setTitle(item.getTitle());
        this.infoWindowFrame.setLocation(
                mainWindowFrame.getX()
                        - mainWindowFrame.getSize().width
                        - this.infoWindowFrame.getSize().width / 2,
                mainWindowFrame.getY());
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
        this.addWindowFrame.setLocation(mainWindowFrame.getX() + mainWindowFrame.getSize().width,
                mainWindowFrame.getY());
        this.addWindowFrame.setVisible(true);
    }

    void packAddWindow() {
        this.addWindowFrame.pack();

    }
    public void closeAddWindow() {
        this.addWindowFrame.setVisible(false);
        mainWindow.updateModel();
    }

    private void deleteConfirmed() {
        confirmDeleteWindowFrame.setVisible(false);
        mainWindow.deleteItem(this.selectedItem);
        mainWindowFrame.pack();
    }

    private void deleteCanceled() {
        confirmDeleteWindowFrame.setVisible(false);
    }

    void closeInfoWindow(TopSecretPieceBE item) {
        infoWindowFrame.setVisible(false);
    }

    private void closeConfirmQuitWindow() {
        confirmQuitWindowFrame.setVisible(false);
    }

    private void systemQuit() {
        System.exit(-1);
    }

    private JFrame createMainWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        mainWindow = new MainWindow(this);
        JPanel contentPane = mainWindow.getMainPanel();
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCloseOperation();
            }
        });
        frame.pack();
        positionOnCenter(frame);
        return frame;
    }

    private JFrame createInfoWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        infoWindow = new InfoWindow(this);
        JPanel contentPane = infoWindow.getMainPanel();
        frame.setContentPane(contentPane);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setUndecorated(true);
        frame.pack();
        return frame;
    }

    private JFrame createConfirmDeleteWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new PopupWindow(this, "Delete this ?", this::deleteCanceled, this::deleteConfirmed).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(mainWindowFrame);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
        frame.pack();
        positionOnCenter(frame);
        return frame;
    }

    private JFrame createConfirmQuitWindowFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(new PopupWindow(this, "Quit LockRepo ?", this::closeConfirmQuitWindow, this::systemQuit).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(mainWindowFrame);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
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
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.pack();
        return frame;
    }

}
