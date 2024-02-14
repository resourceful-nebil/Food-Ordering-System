/*
 * Created by JFormDesigner on Thu Jun 01 09:15:26 EAT 2023
 */

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Orderlist extends JFrame {
    public static int heightScrollable = 0;
    private final double vgap = 25;
    Timer timer;
    public Orderlist() {
        initComponents();
        addOrdersList();
        resizeContainer();
        updateListener();
        this.setResizable(false);
        customTouch();

    }

    public void customTouch() {
        this.getContentPane().setBackground(Color.RED);
        label1.setForeground(Color.WHITE);

    }
    public void resizeContainer() {
        orderContainer.setPreferredSize(new Dimension(orderContainer.getPreferredSize().width, heightScrollable));
        orderContainer.revalidate();
        orderContainer.repaint();
    }

    public void updateListener() {
        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderContainer.removeAll();
                heightScrollable = 0;

                addOrdersList();
                resizeContainer();
            }
        });

        timer.start();
    }

    public void updateOnCheck() {
                orderContainer.removeAll();
                heightScrollable = 0;

                addOrdersList();
                resizeContainer();
    }

    public void addOrdersList() {
        OrderListController controller = new OrderListController();
        ArrayList<ArrayList<ArrayList>> data =  controller.getOrderData();

        String userID = "";
        int quantity = 0;
        String status = "";

        for(int i = 0; i < data.size(); i++) {
            String orderItemText = "Food" + "\t\t\t" + "Quantity\n";
            String decore = new String("_").repeat(100) + "\n";
            orderItemText += decore;

            int index = 0;
            for(int j =0; j < data.get(i).size(); j++) {
                Object row[] = new Object[data.get(i).get(j).size()];

               for(int x = 0; x < row.length; x++) {
                   row[x] = data.get(i).get(j).get(x);
               }

                int id = (int) row[0];
                String userId = (String) row[1];
                userID = userId;

                String foodName = (String) row[2];
                int qty = (int) row[3];
                status = (String) row[4];
                Timestamp timeStamp = (Timestamp) row[5];

                orderItemText += ++index + " ." + foodName + "\t\t\t   " + qty + "\n\n";
            }

            if(status.equals("ready")) {
                continue;
            }

           JPanel item = createPanel(userID, orderItemText);
            orderContainer.add(item);

            orderContainer.revalidate();
            orderContainer.paintImmediately(orderContainer.getVisibleRect());
        }
    }

    public JPanel createPanel(String id, String text) {
        //======== orderItem ========
         JPanel orderItem = new JPanel();
         JLabel userIdLabel = new JLabel();
         JScrollPane orderListField = new JScrollPane();
         JTextArea foodListField = new JTextArea();
         JCheckBox checkbox = new JCheckBox();

         checkbox.setFocusable(false);

          foodListField.setFont(new Font("Segoe UI", Font.BOLD, 14));


           orderItem.setLayout(null);

            //---- userIdLabel ----
            userIdLabel.setText("#" + id);
            userIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
            userIdLabel.setForeground(Color.darkGray);
            orderItem.add(userIdLabel);
            userIdLabel.setBounds(136, 8, 200, userIdLabel.getPreferredSize().height);

            //======== orderListField ========
            {
                orderListField.setViewportView(foodListField);
            }
            orderItem.add(orderListField);
            orderListField.setBounds(210, 45, 715, 120);

            //---- checkbox ----
            checkbox.setText("Is food ready ?");
            checkbox.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
            checkbox.setForeground(Color.darkGray);

            checkbox.addActionListener(e -> checkHandler(e, id, checkbox));
            orderItem.add(checkbox);
            checkbox.setBounds(945, 65, 200, 80);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < orderItem.getComponentCount(); i++) {
                Rectangle bounds = orderItem.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = orderItem.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            orderItem.setMinimumSize(preferredSize);
            orderItem.setPreferredSize(preferredSize);
        }
            foodListField.setText(text);
            foodListField.setEditable(false);
            heightScrollable += orderItem.getPreferredSize().height;

            return orderItem;
        }

    private void checkHandler(ActionEvent e, String id, JCheckBox checkBox) {
        int response = JOptionPane.showConfirmDialog(null, ("Are you sure your finished preparing " + id + "'s orders?"), "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            OrderListController controller = new OrderListController();
            controller.readyUpdate(id);
            updateOnCheck();
        } else if (response == JOptionPane.NO_OPTION) {
            checkBox.setSelected(false);
        }
    }

    private void initComponents() {
        label1 = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        orderContainer = new JPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Customers Order");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        contentPane.add(label1);
        label1.setBounds(5, 0, 1190, 45);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
            .EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax
            .swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,
            12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans
            .PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.
            getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //======== orderContainer ========
                {
                    orderContainer.setMinimumSize(new Dimension(1115, 1000));
                    orderContainer.setLayout(new BoxLayout(orderContainer, BoxLayout.Y_AXIS));


                }
                scrollPane1.setViewportView(orderContainer);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 50, 1195, 600);

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
        setSize(1200, 680);
        setLocationRelativeTo(null);
        }

    private JLabel label1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JPanel orderContainer;
}
