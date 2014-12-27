package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InfoWindow {
    private JPanel mainPanel;
    private JTextField usernameTxtField;
    private JTextField passwordTxtField;
    private JTextField descriptionTxtField;
    private JPanel formPanel;
    private JButton saveBtn;
    private JLabel titleLbl;

    private WindowManager manager;
    /**
     * Item by title
     */
    private TopSecretPieceBE item;

    public InfoWindow(WindowManager manager) {
        this.manager = manager;
        usernameTxtField.setColumns(20);
        passwordTxtField.setColumns(20);
        descriptionTxtField.setColumns(20);
        usernameTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    manager.closeInfoWindow(item);
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
        usernameTxtField.setText(this.item.getUsername());
        passwordTxtField.setText(this.item.getPasswd());
        descriptionTxtField.setText(this.item.getInfo().orElse("no description specified"));
    }
}
