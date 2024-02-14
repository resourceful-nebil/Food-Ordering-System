/*
 * Created by JFormDesigner on Mon May 29 12:45:17 EAT 2023
 */

package Incase;

import UI.AdminDashBoardController;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Nesredin Getahun
 */
public class ADBUI extends JFrame {
    private final String url = "jdbc:mysql://localhost:3310/foodorderingsystem";
    private final String user = "root";
    private final String password = "";
    private final String tableName = "menu";
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static int unique_id = 1000;
    private static int selectedOne = 1000;

    private DefaultTableModel tableModel;
    public ADBUI() {
        AdminDashBoardController controller = new AdminDashBoardController();
        tableModel = new DefaultTableModel(controller.getTableData(), controller.colsName);

        initComponents();
    }


    private void addHandler(ActionEvent e) {
        try {
            String foodName = name.getText();
            double foodPrice = Double.parseDouble(price.getText());
            String url = uploadField.getText();

            if (foodName == null || url == null  || Double.isNaN(foodPrice)) {
                JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
            } else if (foodName == "" || url == ""  || foodPrice < 0) {
                JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
            } else {
                AdminDashBoardController adminDashBoardController = new AdminDashBoardController();
                Object [] row = adminDashBoardController.addMenu(foodName, foodPrice, url);

                tableModel = (DefaultTableModel) menuTable.getModel();
                tableModel.addRow(row);

                menuTable.revalidate();
                menuTable.paintImmediately(menuTable.getVisibleRect());

            }
        }catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please Enter valid number only");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
        }
    }

    private void listSelectionHandler(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = menuTable.getSelectedRow();
            selectedOne = selectedRow;
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
                // Retrieve the data for the selected row
                int id = (int) model.getValueAt(selectedRow, 0);
                unique_id = id;
                String foodName = (String) model.getValueAt(selectedRow, 1);
                double foodPrice = (Double) model.getValueAt(selectedRow, 2);
                String url = (String) model.getValueAt(selectedRow, 3);
                String desc = (String) model.getValueAt(selectedRow, 4);

                name.setText(foodName);
                price.setText(foodPrice + "");
                uploadField.setText(url);


            }
        }
    }

    private void uploadHandler(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\hp\\Pictures");
        int result = fileChooser.showOpenDialog(this);


        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            uploadField.setText(selectedFile.getAbsolutePath());
            AdminDashBoardController controller = new AdminDashBoardController();
            controller.createImageWindow(selectedFile.getAbsolutePath(), this);

        } else {
            JOptionPane.showMessageDialog(this, "Image to be uploaded isn't selected");
        }
    }

    private void deleteHandler(ActionEvent e) {
        if (selectedOne != 1000) {
            DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
            model.removeRow(selectedOne);
            AdminDashBoardController controller = new AdminDashBoardController();
            controller.delete(selectedOne);
            menuTable.revalidate();
            menuTable.paintImmediately(menuTable.getVisibleRect());
        } else {
            JOptionPane.showMessageDialog(this, "Select row before deleting");
        }

        selectedOne = 1000;
    }

    private void clearHandler(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        model.setRowCount(0);
        menuTable.revalidate();
        menuTable.paintImmediately(menuTable.getVisibleRect());

        AdminDashBoardController controller = new AdminDashBoardController();
        controller.clear();
    }

    private void updateHandler(ActionEvent e) {
        if(selectedOne != 1000) {
            DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
            // Update the data for the selected row
            try {
                String foodName = name.getText();
                double foodPrice = Double.parseDouble(price.getText());
                String url = uploadField.getText();

                if (foodName == null || url == null  || Double.isNaN(foodPrice)) {
                    JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
                } else if (foodName == "" || url == "" || foodPrice < 0) {
                    JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
                } else {
                    AdminDashBoardController adminDashBoardController = new AdminDashBoardController();
                    adminDashBoardController.update(unique_id, foodName, foodPrice, url);

                    model.setValueAt(unique_id, selectedOne, 0);
                    model.setValueAt(foodName, selectedOne, 1);
                    model.setValueAt(foodPrice, selectedOne, 2);
                    model.setValueAt(url, selectedOne, 3);

                    menuTable.revalidate();
                    menuTable.paintImmediately(menuTable.getVisibleRect());

                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please Enter valid number only");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Please Enter valid Inputs only");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select row before updating");
        }
    }

    private void thisWindowOpened(WindowEvent e) {
        menuTable.revalidate();
        menuTable.paintImmediately(menuTable.getVisibleRect());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        menuTable = new JTable();
        backBtn = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        name = new JTextField();
        price = new JTextField();
        label3 = new JLabel();
        uploadBtn = new JButton();
        label5 = new JLabel();
        addBtn = new JButton();
        deleteBtn = new JButton();
        clearBtn = new JButton();
        updateBtn = new JButton();
        uploadField = new JTextField();

        //======== this ========
        setMinimumSize(new Dimension(1200, 650));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                    swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border
                    .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog"
                    ,java.awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder
                    ()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
                                                                                                                                                .beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException
                ();}});
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //---- menuTable ----
                menuTable.setFont(new Font("Segoe UI", Font.BOLD, 14));
                menuTable.setShowHorizontalLines(true);
                menuTable.setShowVerticalLines(true);
                menuTable.setRowHeight(25);
                menuTable.setSelectionBackground(new Color(0xf33662));
                menuTable.setSelectionForeground(Color.white);
                scrollPane1.setViewportView(menuTable);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(panel1);
        panel1.setBounds(495, 0, 695, 620);

        //---- backBtn ----
        backBtn.setText("Back");
        backBtn.setFont(backBtn.getFont().deriveFont(Font.BOLD, backBtn.getFont().getSize() + 2f));
        backBtn.setBackground(Color.red);
        backBtn.setForeground(Color.white);
        backBtn.setFocusable(false);
        contentPane.add(backBtn);
        backBtn.setBounds(0, 590, 80, 20);

        //---- label1 ----
        label1.setText("Welcome to Admin DashBoard");
        label1.setFont(new Font("Roboto", Font.BOLD, 28));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(5, 5, 470, 36);

        //---- label2 ----
        label2.setText("Food name");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(label2);
        label2.setBounds(0, 80, 100, 25);

        //---- name ----
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(name);
        name.setBounds(130, 80, 310, 37);

        //---- price ----
        price.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(price);
        price.setBounds(130, 130, 310, 37);

        //---- label3 ----
        label3.setText("Price");
        label3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(label3);
        label3.setBounds(0, 130, 95, 25);

        //---- uploadBtn ----
        uploadBtn.setText("Upload");
        uploadBtn.setHorizontalAlignment(SwingConstants.LEFT);
        uploadBtn.setFont(new Font("Simplified Arabic Fixed", Font.BOLD | Font.ITALIC, 14));
        uploadBtn.setBackground(new Color(0x990b1089, true));
        uploadBtn.setForeground(Color.white);
        uploadBtn.setFocusable(false);
        contentPane.add(uploadBtn);
        uploadBtn.setBounds(350, 185, 90, 35);

        //---- label5 ----
        label5.setText("Upload image");
        label5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(label5);
        label5.setBounds(0, 185, 121, 25);

        //---- addBtn ----
        addBtn.setText("Add");
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        addBtn.setPreferredSize(new Dimension(200, 40));
        addBtn.setFocusable(false);
        addBtn.addActionListener(e -> addHandler(e));
        contentPane.add(addBtn);
        addBtn.setBounds(20, 490, 200, 25);

        //---- deleteBtn ----
        deleteBtn.setText("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        deleteBtn.setPreferredSize(new Dimension(200, 40));
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(e -> deleteHandler(e));
        contentPane.add(deleteBtn);
        deleteBtn.setBounds(20, 520, 200, 25);

        //---- clearBtn ----
        clearBtn.setText("Clear");
        clearBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        clearBtn.setPreferredSize(new Dimension(200, 40));
        clearBtn.setFocusable(false);
        clearBtn.addActionListener(e -> clearHandler(e));
        contentPane.add(clearBtn);
        clearBtn.setBounds(285, 520, 200, 25);

        //---- updateBtn ----
        updateBtn.setText("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        updateBtn.setPreferredSize(new Dimension(200, 40));
        updateBtn.setFocusable(false);
        updateBtn.addActionListener(e -> updateHandler(e));
        contentPane.add(updateBtn);
        updateBtn.setBounds(285, 490, 200, 25);

        //---- uploadField ----
        uploadField.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        uploadField.setEditable(false);
        contentPane.add(uploadField);
        uploadField.setBounds(130, 185, 225, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(1200, 645);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable menuTable;
    private JButton backBtn;
    private JLabel label1;
    private JLabel label2;
    private JTextField name;
    private JTextField price;
    private JLabel label3;
    private JButton uploadBtn;
    private JLabel label5;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton updateBtn;
    private JTextField uploadField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
