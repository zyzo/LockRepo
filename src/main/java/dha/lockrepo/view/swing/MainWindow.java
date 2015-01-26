package dha.lockrepo.view.swing;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import dha.lockrepo.business.TopSecretService;
import dha.lockrepo.business.TopSecretServiceImpl;
import dha.lockrepo.core.LRUtils;
import dha.lockrepo.core.domains.TopSecretPieceBE;
import dha.lockrepo.core.vo.TopSecretPieceVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainWindow {
    private JPanel mainPanel;
    private JList<TopSecretPieceVO> titleList;
    private JList<String> registerDateList;
    private JButton addBtn;
    private JPopupMenu rightClickMenu;
    private JMenuItem rightClickDeleteItm;
    private JMenuItem rightClickOpenItm;
    private JMenuItem rightClickCopyUsernameItm;
    private JMenuItem rightClickCopyPasswordItm;

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
        rightClickOpenItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowManager.openInfoWindow(titleList.getSelectedValue());
            }
        });
        rightClickCopyUsernameItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LRUtils.copyToClipboard(titleList.getSelectedValue().getUsername());
            }
        });
        rightClickCopyPasswordItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LRUtils.copyToClipboard(titleList.getSelectedValue().getPasswd());
            }
        });
        rightClickMenu.add(rightClickOpenItm);
        rightClickMenu.add(rightClickCopyUsernameItm);
        rightClickMenu.add(rightClickCopyPasswordItm);
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

    public void updateModel() {
        DefaultListModel<TopSecretPieceVO> modelsInList = new DefaultListModel<>();
        List<TopSecretPieceBE> models = topSecretService.findAll();
        List<TopSecretPieceVO> modelsVO = TopSecretPieceVO.convert(models);
        modelsVO.forEach(e -> modelsInList.addElement(e));
        this.titleList.setModel(modelsInList);
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
        mainPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.setMinimumSize(new Dimension(352, 313));
        mainPanel.setOpaque(false);
        mainPanel.setVisible(true);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        CellConstraints cc = new CellConstraints();
        mainPanel.add(panel1, cc.xy(3, 1));
        addBtn = new JButton();
        addBtn.setText("Add");
        panel1.add(addBtn, cc.xy(1, 5, CellConstraints.RIGHT, CellConstraints.DEFAULT));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel2, cc.xy(3, 3));
        registerDateList = new JList();
        registerDateList.setEnabled(false);
        registerDateList.setFont(new Font("Lucida Sans Typewriter", registerDateList.getFont().getStyle(), registerDateList.getFont().getSize()));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("3 days ago");
        defaultListModel1.addElement("5 months ago");
        defaultListModel1.addElement("Plus than a year");
        defaultListModel1.addElement("Plus than a year");
        registerDateList.setModel(defaultListModel1);
        registerDateList.setVisible(false);
        panel2.add(registerDateList, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Account");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Register date");
        label2.setVisible(false);
        panel2.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleList = new JList();
        titleList.setFixedCellHeight(20);
        titleList.setFixedCellWidth(20);
        titleList.setFont(new Font("NanumGothic", titleList.getFont().getStyle(), 14));
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        titleList.setModel(defaultListModel2);
        panel2.add(titleList, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, cc.xy(1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, cc.xy(5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.add(panel3, cc.xy(3, 11));
        rightClickMenu = new JPopupMenu();
        rightClickMenu.setInheritsPopupMenu(false);
        rightClickMenu.setLabel("");
        rightClickMenu.setName("");
        rightClickMenu.putClientProperty("html.disable", Boolean.FALSE);
        panel3.add(rightClickMenu, new CellConstraints(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT, new Insets(0, 300, 200, 0)));
        rightClickDeleteItm = new JMenuItem();
        rightClickDeleteItm.setText("Delete");
        panel3.add(rightClickDeleteItm, cc.xy(1, 3));
        rightClickOpenItm = new JMenuItem();
        rightClickOpenItm.setText("Open");
        rightClickOpenItm.setVisible(true);
        panel3.add(rightClickOpenItm, cc.xy(1, 5));
        rightClickCopyUsernameItm = new JMenuItem();
        rightClickCopyUsernameItm.setText("Copy username to clipboard");
        panel3.add(rightClickCopyUsernameItm, cc.xy(1, 7));
        rightClickCopyPasswordItm = new JMenuItem();
        rightClickCopyPasswordItm.setText("Copy password to clipboard");
        panel3.add(rightClickCopyPasswordItm, cc.xy(1, 9));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 10));
        label3.setText("© 2015 zyzo - ver 1.0");
        mainPanel.add(label3, cc.xy(3, 13, CellConstraints.RIGHT, CellConstraints.DEFAULT));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, cc.xy(3, 15, CellConstraints.FILL, CellConstraints.DEFAULT));
        rightClickMenu.setInvoker(titleList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
