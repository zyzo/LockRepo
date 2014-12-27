package dha.lockrepo.view.swing;

import javax.swing.*;

/**
 * Created by zyzo on 27/12/14.
 */
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

    private WindowManager windowManager;

    public AddPieceWindow(WindowManager windowManager) {
        this.windowManager = windowManager;
        usernameTxtField.setColumns(20);
        passwordTxtField.setColumns(20);
        descriptionTxtArea.setRows(3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddPieceWindow");
        frame.setContentPane(new AddPieceWindow(null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
