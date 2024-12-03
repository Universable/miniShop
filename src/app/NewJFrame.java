package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;


public class NewJFrame extends javax.swing.JFrame {

    
        Image img = Toolkit.getDefaultToolkit().getImage("fon.jpg");      

        
        
    int IncreaseXorY=0;
    public static int IsAdminYesNo;
    //Ще бъде променен в зависимост от това дали акаунта е админ или не
    int x=80, y=80;
    Connect db = new Connect();
    
    public NewJFrame(int BoolAdminYesNo, int LoadMenyByDefault) {
        IsAdminYesNo = BoolAdminYesNo;
        initComponents();
        jButton2.setVisible(false);
        AdminMenu.setVisible(false);
        ComboRefresh();
        this.setSize(850, 1000);
        if (IsAdminYesNo == 1) {
        AdminMenu.setVisible(true);            
        }
        if (LoadMenyByDefault==1){
        for (int a = 0; a < jComboBox1.getItemCount(); a++) {
            ArrayList<String> ItemInfo = db.selectWhere(new String[]{"Name","Price","Size","Category", "ImageLink", "ID"},
            new int[] {3}, String.valueOf(jComboBox1.getItemAt(a)), "Meals");
            createboxes(ItemInfo);
            }
         jButton2.setVisible(true);
        jButton1.setVisible(false);
        jComboBox1.setVisible(false);
          jLabel1.setVisible(false);
     
        }

    }
    void createboxes(ArrayList<String> ItemInfo){
    
             for (int i = 0; i < ItemInfo.size(); i++) {
                String[] row = ItemInfo.get(i).split("---");
                //                row[0] - Име
                //                row[1] - Цена
                //                row[2] - Грамаж
                //                row[3] - Категория
                //                row[4] - Изображение
                //                row[5] - ID
                JLabel name = new JLabel();
                jPanel1.add(name);
                name.setSize(200,20);
                name.setLocation(x+260,y+30);
                name.setText(String.valueOf(row[0]));

                JLabel gramaj = new JLabel();
                jPanel1.add(gramaj);
                gramaj.setSize(50,20);
                gramaj.setText(String.valueOf(row[2]) + " гр. ");
                gramaj.setLocation(x+200,y+75);

                JLabel cena = new JLabel();
                jPanel1.add(cena);
                cena.setSize(100,20);
                cena.setText(String.valueOf(row[1]) + " лв.");
                cena.setLocation(x+255,y+75);
                BufferedImage myPicture;
                try {
                    myPicture = ImageIO.read(new File("images/" + row[4]));
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                    add(picLabel);
                    jPanel1.add(picLabel);
                    picLabel.setSize(180,100);
                    picLabel.setLocation(x,y);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                BufferedImage myPicture1;
                try {
                    myPicture1 = ImageIO.read(new File("images/" + row[3] + ".png"));
                    JLabel picLabel1 = new JLabel(new ImageIcon(myPicture1));
                    add(picLabel1);
                    jPanel1.add(picLabel1);
                    picLabel1.setSize(60,60);
                    picLabel1.setLocation(x+190,y+10);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                JButton cart = new JButton();
                jPanel1.add(cart);
                cart.setSize(50, 20);
                cart.setText("🛒");
                cart.setBackground(Color.red);
                cart.setLocation(x+295,y+75);

                cart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(row[5]);
                        db.insert(new String [] {"OrderItemName", "OrderCost", "OrderQuantity", "OrderItemID"},
                            new String [] {row[0], row[1], row[2], row[5]}, "CurrentOrder");
                        JOptionPane.showMessageDialog(null, "Успешно добавено!", "🛒", JOptionPane.PLAIN_MESSAGE);
                    }
                });
                IncreaseXorY +=1;
                if (IncreaseXorY%2==1) x += 360;
                else {
                    y +=130;
                    x -= 360;
                }
            }
   
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AdminMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Добавяне на ястия към поръчка");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Търси");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Изберете категория за сортиране на ястията");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Потвърди поръчка");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Търсене по категория:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(103, 103, 103))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jMenu1.setText("Добави");

        jMenuItem1.setText("Ястие");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Потребител");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        AdminMenu.add(jMenu1);

        setJMenuBar(AdminMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int reply = JOptionPane.showConfirmDialog(null, "Сигурни ли сте, че искате да излезете? Данните от вашат поръчка няма да се запазят!");
        if (reply==JOptionPane.YES_OPTION) {
            db.deleteall("CurrentOrder");
            System.exit(0);
        }
        else{
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
                int x = IsAdminYesNo;
                AddMeal b = new AddMeal(x);
                b.setVisible(true);
                this.dispose();         
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int x = IsAdminYesNo;
        ConfirmOrder a = new ConfirmOrder(x);
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = IsAdminYesNo;
        NewJFrame jframe1 = new NewJFrame(x, 0);
        jframe1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton2.setVisible(true);
        jButton1.setVisible(false);
        jComboBox1.setVisible(false);
        jLabel1.setVisible(false);

        if(jComboBox1.getSelectedItem()=="Всички")
        {
            for (int a = 0; a < jComboBox1.getItemCount(); a++) {
                            ArrayList<String> ItemInfo = db.selectWhere(new String[]{"Name","Price","Size","Category", "ImageLink", "ID"},
                new int[] {3}, String.valueOf(jComboBox1.getItemAt(a)), "Meals");
                            createboxes(ItemInfo);
        }
 }
        else{
            ArrayList<String> ItemInfo = db.selectWhere(new String[]{"Name","Price","Size","Category", "ImageLink", "ID"},
                new int[] {3}, String.valueOf(jComboBox1.getSelectedItem()), "Meals");
                createboxes(ItemInfo);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
                int x = IsAdminYesNo;
                AddClient b = new AddClient(x);
                b.setVisible(true);
                this.dispose();         

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int x = IsAdminYesNo;
                new NewJFrame(x, 0).setVisible(true);
            }
        });
    }
    
    void ComboRefresh(){
        ArrayList<String> sections = new ArrayList<String>();
        sections = db.selectGroup(new String[]{"Category"}, "Category", "Meals");
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Всички");
        for (int i = 0; i < sections.size(); i++) {
            jComboBox1.addItem(sections.get(i).split("---")[0]);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar AdminMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
