/*
 * Created by JFormDesigner on Tue May 30 17:22:15 EAT 2023
 */

package Incase;

import UI.AdminDashBoardController;
import UI.DashboardController;
import UI.LoginController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Nesredin Getahun
 */
public class Auth extends JFrame {
    public Auth() {
        initComponents();
    }

    private void loginHandler(ActionEvent e) {
        String userName = "";
        String pass = "";
        try {
            userName = name.getText();
            pass = new String(password.getPassword());

            if(userName == "" || pass == "") {
                JOptionPane.showMessageDialog(this, "Please enter valid inputs only!!");
                return;
            }
            if(userName == null || pass == null) {
                JOptionPane.showMessageDialog(this, "Please enter valid inputs only!!");
                return;
            }

            LoginController controller = new LoginController();
            Object[] info = controller.getAuth();
            String USER_NAME = (String) info[1];
            String PASSWORD = (String) info[2];

            if(userName.equals(USER_NAME) && pass.equals(PASSWORD)) {
                AdminDashBoardController.start();
                this.dispose();
               // Dashboard.heightScrollable = 0;
                // Dashboard.widthScrollable = 0;
                DashboardController.frameDispose.dispose();


            } else {
                JOptionPane.showMessageDialog(this, "Wrong entry, please check your password and username");
                return;
            }




        }catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid inputs only!!");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
        loginBtn = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        name = new JTextField();
        password = new JPasswordField();
        panel1 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- loginBtn ----
        loginBtn.setText("Login");

        ///////////////////////////Manual//////////////
        loginBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginBtn.setBackground(Color.red);
        loginBtn.setForeground(Color.white);


        loginBtn.setFocusable(false);
        loginBtn.addActionListener(e -> loginHandler(e));
        contentPane.add(loginBtn);
        loginBtn.setBounds(295, 215, 135, 35);

        //---- label2 ----
        label2.setText("Name");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(label2);
        label2.setBounds(25, 65, 90, label2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("Password");
        label3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(label3);
        label3.setBounds(25, 115, 90, label3.getPreferredSize().height);

        //---- name ----
        name.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(name);
        name.setBounds(135, 65, 200, name.getPreferredSize().height);

        //---- password ----
        password.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(password);
        password.setBounds(135, 115, 200, password.getPreferredSize().height);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                    border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER
                    ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font
                    .BOLD,12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(
                new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er"
                        .equals(e.getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Admin Authentication");
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            label1.setFont(new Font("Segoe UI", Font.BOLD, 26));
            label1.setForeground(Color.black);
            panel1.add(label1);
            label1.setBounds(20, 0, 275, 40);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 445, 45);

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
        setSize(445, 300);
        setLocation(700, 100);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
    private JButton loginBtn;
    private JLabel label2;
    private JLabel label3;
    private JTextField name;
    private JPasswordField password;
    private JPanel panel1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
