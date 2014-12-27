package dha.lockrepo.view.swing;

import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretPieceVO;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class MainWindow {
    private JPanel mainPanel;
    private JList<TopSecretPieceVO> titleList;
    private JList<String> registerDateList;
    private JButton addBtn;
    private JPopupMenu rightClickMenu;
    private JMenuItem rightClickDeleteItm;

    private TopSecretService topSecretService;
    private WindowManager windowManager;

    public MainWindow(WindowManager windowManager) {
        this.windowManager = windowManager;
        topSecretService = TopSecretServiceImpl.getInstance();
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
                if (KeyEvent.VK_DELETE == e.getKeyCode()) {
                    windowManager.openConfirmDeleteWindow(titleList.getSelectedValue());
                }
            }
        });

        rightClickDeleteItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowManager.openConfirmDeleteWindow(titleList.getSelectedValue());
            }
        });
        rightClickMenu.add(rightClickDeleteItm);
        updateModel();
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                windowManager.openAddWindow();
            }
        });
    }

    public void deleteItem(TopSecretPieceVO pieceVO) {
        System.out.println("Deleting " + pieceVO.getTitle());
        topSecretService.removePieceById(pieceVO.getId());
        updateModel();
    }

    private void checkRightClickPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            System.out.println("pop up triggering");
            rightClickMenu.show(titleList, e.getX(), e.getY());
            titleList.setSelectedIndex(e.getY() / titleList.getFixedCellWidth());
        }
    }

    public void openInfoWindow(TopSecretPieceVO selected) {
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

    public void updateModel() {
        DefaultListModel<TopSecretPieceVO> modelsInList = new DefaultListModel<TopSecretPieceVO>();
        List<TopSecretPieceBE> models = topSecretService.findAll();
        List<TopSecretPieceVO> modelsVO = TopSecretPieceVO.convert(models);
        modelsVO.forEach(e -> modelsInList.addElement(e));
        this.titleList.setModel(modelsInList);
    }
}
