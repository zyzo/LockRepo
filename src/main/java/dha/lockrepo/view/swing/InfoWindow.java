package dha.lockrepo.view.swing;

import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoWindow {
    private JPanel mainPanel;
    private JTextField usernameTxtField;
    private JTextField passwordTxtField;
    private JTextField descriptionTxtField;
    private JPanel formPanel;
    private JButton saveBtn;
    private JLabel titleLbl;
    private JTextField titleTxtField;

    private WindowManager manager;
    private TopSecretService secretService;
    /**
     * Item by title
     */
    private TopSecretPieceBE item;

    public InfoWindow(WindowManager windowManager) {
        this.manager = windowManager;
        secretService = TopSecretServiceImpl.getInstance();
        usernameTxtField.setColumns(20);
        passwordTxtField.setColumns(20);
        descriptionTxtField.setColumns(20);
        usernameTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    windowManager.closeInfoWindow(item);
                }
            }
        });
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                item.setTitle(usernameTxtField.getText());
                item.setUsername(usernameTxtField.getText());
                item.setPasswd(passwordTxtField.getText());
                secretService.update(item);
                titleTxtField.setVisible(false);
                titleLbl.setText(titleTxtField.getText());
                titleLbl.setVisible(true);
                windowManager.packInfoWindow();
                windowManager.update();
            }
        });
        titleLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    titleLbl.setVisible(false);
                    titleTxtField.setVisible(true);
                    windowManager.packInfoWindow();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("InfoWindow");
        frame.setContentPane(new InfoWindow(null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setItem(TopSecretPieceBE item) {
        this.item = item;
        if (this.item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        System.out.println("Setting item " + item.getTitle());
        titleLbl.setText(this.item.getTitle());
        titleTxtField.setText(this.item.getTitle());
        usernameTxtField.setText(this.item.getUsername());
        passwordTxtField.setText(this.item.getPasswd());
        descriptionTxtField.setText(this.item.getInfo().orElse("no description specified"));
    }
}
