package dha.lockrepo.view.swing;

import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.LRUtils;
import dha.lockrepo.core.domains.TopSecretPieceBE;

import javax.swing.*;
import java.awt.*;
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.setEnabled(true);
        mainPanel.setVisible(true);
        titleLbl = new JLabel();
        titleLbl.setText("Name*");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(titleLbl, cc.xy(3, 5));
        usernameLbl = new JLabel();
        usernameLbl.setText("Username*");
        mainPanel.add(usernameLbl, cc.xy(3, 7));
        usernameTxtField = new JTextField();
        usernameTxtField.setFont(new Font("NanumGothic", Font.BOLD, 14));
        mainPanel.add(usernameTxtField, cc.xy(5, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        passwdLbl = new JLabel();
        passwdLbl.setText("Password*");
        mainPanel.add(passwdLbl, cc.xy(7, 7));
        passwordTxtField = new JTextField();
        passwordTxtField.setFont(new Font("NanumGothic", Font.BOLD, 14));
        mainPanel.add(passwordTxtField, cc.xy(9, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        titleTxtField = new JTextField();
        titleTxtField.setFont(new Font("NanumGothic", Font.BOLD, 14));
        mainPanel.add(titleTxtField, cc.xyw(5, 5, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, cc.xy(1, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, cc.xy(11, 5, CellConstraints.DEFAULT, CellConstraints.FILL));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, cc.xy(5, 13, CellConstraints.FILL, CellConstraints.DEFAULT));
        descriptionLbl = new JLabel();
        descriptionLbl.setText("Description");
        descriptionLbl.setVisible(false);
        mainPanel.add(descriptionLbl, cc.xy(3, 9));
        descriptionTxtArea = new JTextArea();
        descriptionTxtArea.setVisible(false);
        mainPanel.add(descriptionTxtArea, cc.xyw(5, 9, 3, CellConstraints.FILL, CellConstraints.FILL));
        confirmButton = new JButton();
        confirmButton.setText("Create");
        mainPanel.add(confirmButton, cc.xyw(7, 11, 3));
        errorMessageLbl = new JLabel();
        errorMessageLbl.setForeground(new Color(-4510162));
        errorMessageLbl.setText("Please fill required fields");
        errorMessageLbl.setVisible(false);
        mainPanel.add(errorMessageLbl, cc.xyw(3, 3, 5));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
