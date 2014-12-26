package dha.lockrepo.view.swing;

import javax.swing.*;

/**
 * Created by zyzo on 26/12/14.
 */
public class InfoWindow {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

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
}
