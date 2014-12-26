package dha.lockrepo.view.swing;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;

public class MainWindow {
    private JPanel mainPanel;
    private JList<String> registerDateList;
    private JButton addBtn;
    private JList<String> titleList;
    private JPopupMenu rightClickMenu;
    private JMenuItem rightClickDeleteItm;

    private WindowManager windowManager;

    public MainWindow(WindowManager windowManager) {
        this.windowManager = windowManager;
        titleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 2) {
                        openInfoWindow(titleList.getSelectedValue());
                    }
                } else {
                    checkRightClickPopup(e);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                checkRightClickPopup(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkRightClickPopup(e);
            }
        });
        titleList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               if(KeyEvent.VK_DELETE == e.getKeyCode()) {
                   deleteItem();
               }
            }
        });

        rightClickDeleteItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });
        rightClickMenu.add(rightClickDeleteItm);
    }

    private void deleteItem() {
        System.out.println("Deleting");
    }

    private void checkRightClickPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            System.out.println("pop up triggering");
            rightClickMenu.show(titleList, e.getX(), e.getY());
        }
    }

    public void openInfoWindow(String selected) {
        System.out.println("Opening info window for " + selected);
        windowManager.openInfoWindow(selected);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow(null).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        rightClickDeleteItm = new JMenuItem("Delete");
    }
}
