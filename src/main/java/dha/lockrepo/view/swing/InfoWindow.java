package dha.lockrepo.view.swing;

import dha.lockrepo.core.domains.TopSecretBE;
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
    /**
     * Item by title
     */
    private TopSecretPieceBE item;

    public InfoWindow() {
        usernameTxtField.setColumns(20);
        passwordTxtField.setColumns(20);
        descriptionTxtField.setColumns(20);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InfoWindow");
        frame.setContentPane(new InfoWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setItem(TopSecretPieceBE item) {
        this.item = item;
        if (this.item != null) {
            usernameTxtField.setText(this.item.getUsername());
            passwordTxtField.setText(this.item.getPasswd());
            descriptionTxtField.setText(this.item.getInfo().orElse("no description specified"));
        }
    }
}
