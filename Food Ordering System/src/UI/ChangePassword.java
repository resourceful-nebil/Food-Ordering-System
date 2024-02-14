/*
 * Created by JFormDesigner on Wed May 31 16:33:36 EAT 2023
 */

package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangePassword extends JFrame {
    public ChangePassword() {
        initComponents();
        customTouch();
        updateBtn.setFocusable(false);
        cancelBtn.setFocusable(false);
    }

    public void customTouch() {
        this.setResizable(false);
        // this.getContentPane().setBackground(Color.BLUE);
        panel1.setBackground(Color.RED);
        panel1.setOpaque(true);
    }

    private void updateHandler(ActionEvent e) {
        PasswordChangeController controller = new PasswordChangeController();
        Object [] data = controller.getAuth();
        String authName = (String) data[1];
        String authPass = (String) data[2]; 
        int id = (int) data[0];
        System.out.println("ID: " + id);
        
        String oldP = "";
        String newP = "";
        String confP = "";
        String newUser = "";
        
        try {
          oldP = new String(oldPass.getPassword());
          newP = new String(newPass.getPassword());
          confP = new String(confPass.getPassword());
          newUser = new String(newUserName.getText());
          
          if(oldP == "" || newP == "" || confP == "" || newUser == "") {
              JOptionPane.showMessageDialog(this, "Please fill all inputs!");
              return;
          }
          
          if(oldP == null || newP == null || confP == null || newUser == null) {
              JOptionPane.showMessageDialog(this, "Please fill all inputs!");
              return;
          }
          
         if(!oldP.equals(authPass)) {
             JOptionPane.showMessageDialog(this, "Wrong password, try again");
             return;
         }

            if(!newP.equals(confP)) {
                JOptionPane.showMessageDialog(this, "Wrong confirmation password, try again");
                return;
            }
            
            if(newP.equals(confP) && oldP.equals(authPass)) {
                System.out.println("HERE");
                controller.updateAuth(id, newUser, newP);
                reset();
            }
          
        }catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid inputs only!!");
        }
    }

    private void cancelHandler(ActionEvent e) {
        reset();
    }

    private void reset() {
        oldPass.setText("");
        newPass.setText("");
        confPass.setText("");
        newUserName.setText("");
        this.dispose();
    }

    private void initComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        newUserName = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        updateBtn = new JButton();
        cancelBtn = new JButton();
        label5 = new JLabel();
        oldPass = new JPasswordField();
        newPass = new JPasswordField();
        confPass = new JPasswordField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder( 0
            , 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM
            , new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,
            panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("Change password");
            label1.setFont(new Font("Segoe UI", Font.BOLD, 28));
            label1.setForeground(Color.WHITE);
            panel1.add(label1);
            label1.setBounds(5, 0, 260, 40);

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
        panel1.setBounds(0, 0, 410, 40);

        //---- newUserName ----
        newUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(newUserName);
        newUserName.setBounds(155, 110, 200, 30);

        //---- label2 ----
        label2.setText("Old password");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(label2);
        label2.setBounds(35, 70, 110, label2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("New user name");
        label3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(35, 110), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("New password");
        label4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(label4);
        label4.setBounds(35, 155, 115, label4.getPreferredSize().height);

        //---- updateBtn ----
        updateBtn.setText("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        updateBtn.setBackground(Color.DARK_GRAY);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.addActionListener(e -> updateHandler(e));
        contentPane.add(updateBtn);
        updateBtn.setBounds(255, 285, 135, 35);

        //---- cancelBtn ----
        cancelBtn.setText("Cancel");
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e -> cancelHandler(e));
        contentPane.add(cancelBtn);
        cancelBtn.setBounds(255, 330, 135, 35);

        //---- label5 ----
        label5.setText("Confirm pass");
        label5.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentPane.add(label5);
        label5.setBounds(35, 195, 115, 22);

        //---- oldPass ----
        oldPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(oldPass);
        oldPass.setBounds(155, 70, 200, 30);

        //---- newPass ----
        newPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(newPass);
        newPass.setBounds(155, 155, 200, 30);

        //---- confPass ----
        confPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPane.add(confPass);
        confPass.setBounds(155, 195, 200, 30);

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
        setSize(415, 410);
        setLocation(700, 100);

    }

    private JPanel panel1;
    private JLabel label1;
    private JTextField newUserName;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton updateBtn;
    private JButton cancelBtn;
    private JLabel label5;
    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField confPass;
}
