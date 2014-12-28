package dha.lockrepo.view.swing;

import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.LRUtils;
import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPieceWindow {
    JPanel mainPanel;
    private JTextField titleTxtField;
    private JTextField usernameTxtField;
    private JTextField passwordTxtField;
    private JTextArea descriptionTxtArea;
    private JLabel titleLbl;
    private JLabel usernameLbl;
    private JLabel passwdLbl;
    private JLabel descriptionLbl;
    private JButton confirmButton;
    private JLabel errorMessageLbl;

    private WindowManager windowManager;
    private TopSecretService secretService;

    public AddPieceWindow(WindowManager windowManager) {
        this.windowManager = windowManager;
        secretService = TopSecretServiceImpl.getInstance();
        usernameTxtField.setColumns(10);
        passwordTxtField.setColumns(10);
        descriptionTxtArea.setRows(3);
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String title = titleTxtField.getText();
                String username = usernameTxtField.getText();
                String password = passwordTxtField.getText();
                //String description = descriptionTxtArea.getText();
                if (LRUtils.isNullOrEmpty(title)
                        || LRUtils.isNullOrEmpty(username)
                        || LRUtils.isNullOrEmpty(password)) {
                    errorMessageLbl.setVisible(true);
                    AddPieceWindow.this.windowManager.packAddWindow();
                } else {
                    errorMessageLbl.setVisible(false);
                    TopSecretPieceBE piece = new TopSecretPieceBE(1L, title, username, password, "???");
                    secretService.addTopSecretPiece(piece);
                    AddPieceWindow.this.windowManager.closeAddWindow();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddPieceWindow");
        frame.setContentPane(new AddPieceWindow(null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
