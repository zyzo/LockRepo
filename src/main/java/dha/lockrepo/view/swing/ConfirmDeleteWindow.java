package dha.lockrepo.view.swing;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfirmDeleteWindow {
    JPanel mainPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel messageLbl;

    private WindowManager windowManager;

    public ConfirmDeleteWindow(WindowManager windowManager) {
        this.windowManager = windowManager;
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Canceling");
                windowManager.deleteCanceled();
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Confirmed");
                windowManager.deleteConfirmed();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConfirmDeleteWindow");
        frame.setContentPane(new ConfirmDeleteWindow(null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
