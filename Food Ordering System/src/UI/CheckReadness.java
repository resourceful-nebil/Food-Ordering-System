/*
 * Created by JFormDesigner on Thu Jun 01 20:00:09 EAT 2023
 */

package UI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;

public class CheckReadness extends JFrame {
    public CheckReadness() {
        initComponents();
        reset();
        customTouch();

    }

    public void customTouch() {
        this.setResizable(false);
        // this.getContentPane().setBackground(Color.BLUE);
//        idLabel.setForeground(Color.WHITE);
//        readyLabel.setForeground(Color.WHITE);
    }

    private void searchHandler(ActionEvent e) {
        String searchInput = searchField.getText();
        System.out.println("Search: " + searchInput);
        CheckReadnessController controller = new CheckReadnessController();
        ArrayList<ArrayList<ArrayList>> data =  controller.getOrderData();

        String userID = "";
        int quantity = 0;
        String status = "";
        String timeSmp = "";
        String stat = "";
        boolean flag = false;

        String orderItemText = "Food" + "\t\t" + "Quantity\n";
        String decore = new String("_").repeat(50) + "\n";
        orderItemText += decore;


        for(int i = 0; i < data.size(); i++) {
            int index = 0;
            for(int j =0; j < data.get(i).size(); j++) {
                Object row[] = new Object[data.get(i).get(j).size()];

                for(int x = 0; x < row.length; x++) {
                    row[x] = data.get(i).get(j).get(x);
                }

                int id = (int) row[0];
                String userId = (String) row[1];

                String foodName = (String) row[2];
                int qty = (int) row[3];
                status = (String) row[4];
                Timestamp timeStamp = (Timestamp) row[5];

                if(userId.equals(searchInput)) {
                    flag = true;
                    userID = userId;
                    orderItemText += ++index + " ." + foodName + "\t\t   " + qty + "\n\n";
                    timeSmp = timeStamp.toString();
                    stat = status;
                }
            }
        }

        if(flag) {
            idLabel.setText("#" + userID);
            readyLabel.setText(stat);
            orderListField.setText(orderItemText);
        } else {
            readyLabel.setText("Wrong ID");
            idLabel.setText("#?");
            orderListField.setText("");
        }

    }

    public void reset() {
        idLabel.setText("");
        readyLabel.setText("");
        orderListField.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
        searchField = new JTextField();
        searchBtn = new JButton();
        scrollPane1 = new JScrollPane();
        orderListField = new JTextArea();
        idLabel = new JLabel();
        readyLabel = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(searchField);
        searchField.setBounds(70, 20, 220, 40);

        //---- searchBtn ----
        searchBtn.setText("Search");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchBtn.addActionListener(e -> searchHandler(e));
        contentPane.add(searchBtn);
        searchBtn.setBounds(285, 20, 80, 40);

        //======== scrollPane1 ========
        {

            //---- orderListField ----
            orderListField.setFont(new Font("Segoe UI", Font.BOLD, 14));
            scrollPane1.setViewportView(orderListField);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(70, 105, 320, 105);

        //---- idLabel ----
        idLabel.setText("#12345");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contentPane.add(idLabel);
        idLabel.setBounds(30, 80, 75, 20);

        //---- readyLabel ----
        readyLabel.setText("isn't ready");
        readyLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        contentPane.add(readyLabel);
        readyLabel.setBounds(400, 130, 105, 35);

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
        setSize(540, 305);
        setLocation(700, 100);
        }

    private JTextField searchField;
    private JButton searchBtn;
    private JScrollPane scrollPane1;
    private JTextArea orderListField;
    private JLabel idLabel;
    private JLabel readyLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
