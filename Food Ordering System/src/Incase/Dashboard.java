package Incase;

import UI.DashboardController;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;


public class Dashboard extends JFrame {
    private int x = 0;
    private double total = 0.0;
    private double tax = 0.15;

    public Dashboard() {
        initComponents();
        init();
        fillMenu();

    }

    public void adjustSize() {
        Dimension preferredSize = new Dimension();
        double height = 0;
        double width = 0;
        for(int i = 0; i < containerPanel.getComponentCount(); i++) {
            Rectangle bounds = containerPanel.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            height += preferredSize.width;
            width += preferredSize.height;
        }

        containerPanel.setPreferredSize(new Dimension((int)width, (int)height));
        containerPanel.paintImmediately(containerPanel.getVisibleRect());

    }
    public void init(){
        setTime();
    }
    public boolean qtyIsZero(int qty){
        if(qty == 0){
            JOptionPane.showMessageDialog(null,"Please Increse The Quantity!");
            return false;
        }
        return true;
    }
    public void reset(){
        total =0.0;
        x=0;
        btnTotal.setEnabled(true);

        spinner1.setValue(0);

        textFieldTax.setText("0.0");
        textFieldSubTotal.setText("0.0");
        textFieldTotal.setText("0.0");
        checkBox1.setSelected(false);
    }
    public void bigPrint(){
        textFieldTax.setText(String.valueOf(String.format("%.2f",tax*total)));
        textFieldSubTotal.setText(String.valueOf(String.format("%.2f",total)));
        textFieldTotal.setText(String.valueOf(String.format("%.2f",total+(tax*total))));

    }
    public void kkCafe(){
        int purchaseId = 15020 + (int) (Math.random() * 80800);
        textArea.setText("*******************************KK CAFE**********************************"+"\n"+"Time: "+
                JTxTime.getText()+"      "+
                "Date:"+JTxDate.getText()+"\n"+
                "Purchase Id: " + purchaseId + "\n"
                +"****************************************************************************\n\n"
                +"Items:"+"\t\t\t"+"Price(Birr)\n");

    }

    private void btnExit(ActionEvent e) {
        System.exit(0);
    }

    private void btnReset(ActionEvent e) {
        reset();
    }

    public JPanel createItem(String foodName, double foodPrice, String path) {
        JPanel item = new JPanel();
        JLabel itemPriceLabel = new JLabel();
        JLabel itemQtyLabel = new JLabel();
        JLabel itemPurchaseLabel = new JLabel();
        JSpinner spinner1 = new JSpinner();
        JLabel itemNameLabel = new JLabel();
        JLabel itemPrice = new JLabel();
        JCheckBox checkBox1 = new JCheckBox();
        Image image = new ImageIcon(path).getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        //======== item ========
        item.setPreferredSize(new Dimension(200, 200));
        item.setLayout(null);


        //---- itemPriceLabel ----
        itemPriceLabel.setText("Price:");
        itemPriceLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        item.add(itemPriceLabel);
        itemPriceLabel.setBounds(new Rectangle(new Point(10, 120), itemPriceLabel.getPreferredSize()));

        //---- itemQtyLabel ----
        itemQtyLabel.setText("Quantity:");
        itemQtyLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        item.add(itemQtyLabel);
        itemQtyLabel.setBounds(10, 140, itemQtyLabel.getPreferredSize().width, 20);

        //---- itemPurchaseLabel ----
        itemPurchaseLabel.setText("Purchase:");
        itemPurchaseLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        item.add(itemPurchaseLabel);
        itemPurchaseLabel.setBounds(new Rectangle(new Point(10, 175), itemPurchaseLabel.getPreferredSize()));
        item.add(spinner1);
        spinner1.setBounds(65, 140, 49, spinner1.getPreferredSize().height);

        //---- itemNameLabel ----
        itemNameLabel.setText(foodName);
        itemNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        item.add(itemNameLabel);
        itemNameLabel.setBounds(45, 100, 105, itemNameLabel.getPreferredSize().height);

        //---- itemPrice ----
        itemPrice.setText(foodPrice + " Birr");
        itemPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
        item.add(itemPrice);
        itemPrice.setBounds(new Rectangle(new Point(65, 120), itemPrice.getPreferredSize()));

        //---- checkBox1 ----
        checkBox1.addActionListener(e -> checkBox1(e, spinner1, checkBox1, itemNameLabel));
        item.add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(80, 175), checkBox1.getPreferredSize()));
        item.add(imageLabel);
        imageLabel.setBounds(0, 0, 200, 90);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < item.getComponentCount(); i++) {
                Rectangle bounds = item.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = item.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            item.setMinimumSize(preferredSize);
            item.setPreferredSize(preferredSize);
        }

        return item;
    }

    private void checkBox1(ActionEvent e, JSpinner spinner1, JCheckBox checkBox1, JLabel itemNameLabel) {
        int qty = Integer.parseInt(spinner1.getValue().toString());
        if(qtyIsZero(qty) && checkBox1.isSelected()){
            x++;
            if(x == 1){
                kkCafe();
            }
            double price = qty*3.0;
            total +=price;
            textArea.setText(textArea.getText()+x+". "+itemNameLabel.getText()+"\t\t\t"+String.format("%.2f",price)+"\n");
            bigPrint();

        }else{
            checkBox1.setSelected(false);

        }
    }
    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException ex){
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE,null,ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEE, dd-MM-yyyy");
                    String time = tf.format(date);
                    JTxTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    JTxDate.setText(df.format(date));

                }
            }
        }).start();
    }

    private void btnTotal(ActionEvent e) {
        if(total == 0.0){
            JOptionPane.showMessageDialog(null,"You haven't selected any food!");
        }else{
            textArea.setText(textArea.getText()
                    +"\n****************************************************************************\n"
                    +"Tax: \t\t\t"+(String.format("%.2f",tax*total))+"\n"
                    +"Subtotal: \t\t\t"+(String.format("%.2f",total))+"\n"
                    +"Total: \t\t\t"+(String.format("%.2f",total+(tax*total)))+"\n\n"
                    +"\n*******************************Thank You**********************************"
            );
            btnTotal.setEnabled(false);
        }

    }

    private void btnReceipt(ActionEvent e) {
        if(total != 0 ){
            if(!btnTotal.isEnabled()){
                try{
                    textArea.print();
                }catch (PrinterException ex){
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE,null,ex);
                }
            }else{
                JOptionPane.showMessageDialog(null,"First, Calculate the total!");

            }
        }else{
            JOptionPane.showMessageDialog(null,"You haven't purchased yet!");

        }

    }

    private void adminHandler(ActionEvent e) {
        // TODO add your code here
    }

    private void fillMenu() {
        DashboardController controller = new DashboardController();
        Object[][] data = controller.getMenuData();
        for (Object[] info:data
        ) {
            String foodName = (String) info[1];
            double foodPrice = (double) info[2];
            String path = (String) info[3];
            JPanel panel = createItem(foodName, foodPrice, path);
            this.containerPanel.add(panel);
            containerPanel.revalidate();
            containerPanel.paintImmediately(containerPanel.getVisibleRect());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
        panel3 = new JPanel();
        panel4 = new JPanel();
        mainLabel = new JLabel();
        JTxTime = new JLabel();
        JTxDate = new JLabel();
        scrollPane1 = new JScrollPane();
        containerPanel = new JPanel();

        label4 = new JLabel();
        subLabel = new JLabel();
        panel20 = new JPanel();
        btnTotal = new JButton();
        btnReceipt = new JButton();
        btnReset = new JButton();
        btnExit = new JButton();
        adminBtn = new JButton();
        panel21 = new JPanel();
        textFieldTax = new JTextField();
        textFieldSubTotal = new JTextField();
        textFieldTotal = new JTextField();
        label96 = new JLabel();
        label97 = new JLabel();
        label98 = new JLabel();
        scrollPane2 = new JScrollPane();
        textArea = new JTextArea();

        //======== this ========
        setMinimumSize(new Dimension(1200, 650));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel3 ========
        {
            panel3.setBackground(Color.white);
            panel3.setPreferredSize(new Dimension(1200, 650));
            panel3.setMinimumSize(new Dimension(1200, 650));
            panel3.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
                    swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border
                    . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog"
                    , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel3. getBorder
                    () ) ); panel3. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
                                                                                                                                                          . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException
                ( ) ;} } );
            panel3.setLayout(null);

            //======== panel4 ========
            {
                panel4.setPreferredSize(new Dimension(200, 35));
                panel4.setBackground(new Color(0xe6e6e6));
                panel4.setLayout(null);

                //---- mainLabel ----
                mainLabel.setText("KK Cafe ");
                mainLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
                panel4.add(mainLabel);
                mainLabel.setBounds(25, 0, 175, 50);

                //---- JTxTime ----
                JTxTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
                panel4.add(JTxTime);
                JTxTime.setBounds(840, 5, 130, 30);

                //---- JTxDate ----
                JTxDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
                panel4.add(JTxDate);
                JTxDate.setBounds(1055, 5, 150, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel4.getComponentCount(); i++) {
                        Rectangle bounds = panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel4.setMinimumSize(preferredSize);
                    panel4.setPreferredSize(preferredSize);
                }
            }

            panel3.add(panel4);
            panel4.setBounds(0, 0, 1210, 40);

            //======== scrollPane1 ========
            {
                scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                //======== containerPanel ========
                {
                    containerPanel.setPreferredSize(new Dimension(200, 200));
                    containerPanel.setBorder(new LineBorder(new Color(0xe6e6e6e6, true), 2, true));
                    containerPanel.setBackground(new Color(0xfafafa));
                    containerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

                    ////////////////////////////////////////////////////////////////////////

                    //containerPanel.add(item);

                }
                scrollPane1.setViewportView(containerPanel);
            }

            panel3.add(scrollPane1);
            scrollPane1.setBounds(5, 60, 825, 490);

            //---- subLabel ----
            subLabel.setText("MENU ITEMS");
            subLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            subLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel3.add(subLabel);
            subLabel.setBounds(5, 40, 820, 25);

            //======== panel20 ========
            {
                panel20.setPreferredSize(new Dimension(300, 300));
                panel20.setBorder(new LineBorder(new Color(0xe6e6e6), 2, true));
                panel20.setBackground(new Color(0xd6d9df));
                panel20.setLayout(null);

                //---- btnTotal ----
                btnTotal.setText("Total");
                btnTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
                btnTotal.setBackground(new Color(0x00cccc));
                btnTotal.setForeground(Color.darkGray);
                btnTotal.addActionListener(e -> btnTotal(e));
                panel20.add(btnTotal);
                btnTotal.setBounds(10, 15, 135, 35);

                //---- btnReceipt ----
                btnReceipt.setText("Receipt");
                btnReceipt.setFont(new Font("Times New Roman", Font.BOLD, 20));
                btnReceipt.setBackground(Color.green);
                btnReceipt.setForeground(Color.darkGray);
                btnReceipt.addActionListener(e -> btnReceipt(e));
                panel20.add(btnReceipt);
                btnReceipt.setBounds(160, 15, 135, 35);

                //---- btnReset ----
                btnReset.setText("Reset");
                btnReset.setFont(new Font("Times New Roman", Font.BOLD, 20));
                btnReset.setBackground(Color.yellow);
                btnReset.setForeground(Color.darkGray);
                btnReset.addActionListener(e -> btnReset(e));
                panel20.add(btnReset);
                btnReset.setBounds(310, 15, 135, 35);

                //---- btnExit ----
                btnExit.setText("Exit");
                btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
                btnExit.setBackground(Color.red);
                btnExit.setForeground(Color.white);
                btnExit.addActionListener(e -> btnExit(e));
                panel20.add(btnExit);
                btnExit.setBounds(620, 15, 135, 35);

                //---- adminBtn ----
                adminBtn.setText("Admin");
                adminBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
                adminBtn.setBackground(Color.white);
                adminBtn.setForeground(Color.darkGray);
                adminBtn.addActionListener(e -> adminHandler(e));
                panel20.add(adminBtn);
                adminBtn.setBounds(465, 15, 135, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel20.getComponentCount(); i++) {
                        Rectangle bounds = panel20.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel20.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel20.setMinimumSize(preferredSize);
                    panel20.setPreferredSize(preferredSize);
                }
            }
            panel3.add(panel20);
            panel20.setBounds(5, 560, 825, 65);

            //======== panel21 ========
            {
                panel21.setPreferredSize(new Dimension(200, 200));
                panel21.setMinimumSize(new Dimension(335, 650));
                panel21.setLayout(null);

                //---- textFieldTax ----
                textFieldTax.setFont(new Font("Times New Roman", Font.BOLD, 20));
                textFieldTax.setEditable(false);
                textFieldTax.setText("0.0");
                textFieldTax.setHorizontalAlignment(SwingConstants.CENTER);
                textFieldTax.setBackground(Color.white);
                panel21.add(textFieldTax);
                textFieldTax.setBounds(170, 455, 160, 30);

                //---- textFieldSubTotal ----
                textFieldSubTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
                textFieldSubTotal.setEditable(false);
                textFieldSubTotal.setText("0.0");
                textFieldSubTotal.setHorizontalAlignment(SwingConstants.CENTER);
                textFieldSubTotal.setBackground(Color.white);
                panel21.add(textFieldSubTotal);
                textFieldSubTotal.setBounds(170, 495, 160, 30);

                //---- textFieldTotal ----
                textFieldTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
                textFieldTotal.setEditable(false);
                textFieldTotal.setText("0.0");
                textFieldTotal.setHorizontalAlignment(SwingConstants.CENTER);
                textFieldTotal.setBackground(Color.white);
                panel21.add(textFieldTotal);
                textFieldTotal.setBounds(170, 530, 160, 30);

                //---- label96 ----
                label96.setText("Tax:");
                label96.setFont(new Font("Times New Roman", Font.BOLD, 20));
                panel21.add(label96);
                label96.setBounds(new Rectangle(new Point(25, 460), label96.getPreferredSize()));

                //---- label97 ----
                label97.setText("Sub Total:");
                label97.setFont(new Font("Times New Roman", Font.BOLD, 20));
                panel21.add(label97);
                label97.setBounds(25, 500, 95, 23);

                //---- label98 ----
                label98.setText("Total:");
                label98.setFont(new Font("Times New Roman", Font.BOLD, 20));
                panel21.add(label98);
                label98.setBounds(25, 535, 70, 23);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(textArea);
                }
                panel21.add(scrollPane2);
                scrollPane2.setBounds(0, 0, 370, 415);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel21.getComponentCount(); i++) {
                        Rectangle bounds = panel21.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel21.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel21.setMinimumSize(preferredSize);
                    panel21.setPreferredSize(preferredSize);
                }
            }
            panel3.add(panel21);
            panel21.setBounds(840, 45, 370, 580);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel3);
        panel3.setBounds(0, 0, 1380, 905);

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
        setSize(1230, 675);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - NESREDIN GETAHUN
    private JPanel panel3;
    private JPanel panel4;
    private JLabel mainLabel;
    private JLabel JTxTime;
    private JLabel JTxDate;
    private JScrollPane scrollPane1;
    private JPanel containerPanel;
    private JPanel item;
    private JLabel label4;
    private JLabel itemPriceLabel;
    private JLabel itemQtyLabel;
    private JLabel itemPurchaseLabel;
    private JSpinner spinner1;
    private JLabel itemNameLabel;
    private JLabel itemPrice;
    private JCheckBox checkBox1;
    private JLabel imageLabel;
    private JLabel subLabel;
    private JPanel panel20;
    private JButton btnTotal;
    private JButton btnReceipt;
    private JButton btnReset;
    private JButton btnExit;
    private JButton adminBtn;
    private JPanel panel21;
    private JTextField textFieldTax;
    private JTextField textFieldSubTotal;
    private JTextField textFieldTotal;
    private JLabel label96;
    private JLabel label97;
    private JLabel label98;
    private JScrollPane scrollPane2;
    private JTextArea textArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
