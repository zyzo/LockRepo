package dha.lockrepo.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupWindow {
    JPanel mainPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private JLabel messageLbl;

    private WindowManager windowManager;

    public PopupWindow(WindowManager windowManager, String message, Runnable onCancel, Runnable onConfirm) {
        this.mainPanel.setPreferredSize(new Dimension(200, 75));
        this.messageLbl.setText(message);
        this.windowManager = windowManager;
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Canceling");
                onCancel.run();
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Confirmed");
                onConfirm.run();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConfirmDeleteWindow");
        frame.setContentPane(new PopupWindow(null, "asdas", System.out::println, null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
