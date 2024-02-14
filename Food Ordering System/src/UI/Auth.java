/*
 * Created by JFormDesigner on Tue May 30 17:22:15 EAT 2023
 */

package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Auth extends JFrame {
    public Auth() {
        initComponents();
        customTouch();
    }

    private void customTouch() {
        this.setResizable(false);
        // this.getContentPane().setBackground(Color.BLUE);
        panel1.setBackground(new Color(255, 0, 0));
        panel1.setOpaque(true);
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
            Dashboard.heightScrollable = 0;
            Dashboard.widthScrollable = 0;
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
        loginBtn = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        name = new JTextField();
        password = new JPasswordField();
        panel1 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- loginBtn ----
        loginBtn.setText("Login");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setFocusable(false);
        loginBtn.setBackground(Color.DARK_GRAY);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> loginHandler(e));
        contentPane.add(loginBtn);
        loginBtn.setBounds(315, 180, 120, 35);

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
        name.setBounds(115, 65, 220, name.getPreferredSize().height);

        //---- password ----
        password.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(password);
        password.setBounds(115, 115, 220, password.getPreferredSize().height);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.
            red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Admin Authentication");
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            label1.setFont(new Font("Segoe UI", Font.BOLD, 26));
            label1.setBackground(new Color(0xFFFFFF));
            label1.setForeground(Color.WHITE);
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
        setSize(445, 260);
        setLocation(700, 100);
          }

    private JButton loginBtn;
    private JLabel label2;
    private JLabel label3;
    private JTextField name;
    private JPasswordField password;
    private JPanel panel1;
    private JLabel label1;
    }
