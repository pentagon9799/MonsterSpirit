/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricylebin;

import septian.*;
import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import hilmi.DBAksess;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class scoutPanel extends javax.swing.JPanel {

    /**
     * Creates new form scoutPanel
     */
    Random r = new Random(1);
    private int[][] nchar;
    private int UID=1;
    private int maxMoney=1000000000;
    private int money=100000000;
    private int reqvalue=9000;
    private DBAksess dba = new DBAksess();
    
   // Color color ;//= new Color(150, 255, 243);
    public scoutPanel() {
        initComponents();
//        this.gold.setMaximum(maxMoney);
//        this.gold.setMinimum(0);
//        this.gold.setValue(money);
//        this.gold.setString(money+"/"+maxMoney+" Gold");
//        this.gold.setForeground(Color.black);
//        this.gold.setBackground(Color.yellow);
        setImage(1,"noIcon");
    }
    private static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) 
        {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        return sb.toString();
    }
    private void setImage(int monsterID, String gainedchar)
    {
        img_1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+gainedchar+".png")));
        img_1.setBorder(new LineBorder(Color.RED, 5));
        img_2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+gainedchar+".png")));
        img_2.setBorder(new LineBorder(Color.RED, 5));
        img_3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+gainedchar+".png")));
        img_3.setBorder(new LineBorder(Color.RED, 5));
        img_4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+gainedchar+".png")));
        img_4.setBorder(new LineBorder(Color.RED, 5));
        img_5.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+gainedchar+".png")));
        img_5.setBorder(new LineBorder(Color.RED, 5));       
    }
    private void scout(int count, JLabel label) 
    {
        if(money>=reqvalue){
            money -=(reqvalue*count);
//            this.gold.setString(money+"/"+maxMoney+" Gold");
//            this.gold.setValue(money);
            nchar= new int[count][1];
            int x=0;
            for(int j=0;j<count;j++)
            {
                try {
                    //int result = r.nextInt(6);
                    int[] result = new int[8];
                    result[0]=r.nextInt(6);result[1]=r.nextInt(8);
                    result[2]=r.nextInt(8);result[3]=r.nextInt(8);
                    result[4]=r.nextInt(8);result[5]=r.nextInt(5);
                    result[6]=r.nextInt(5);result[7]=r.nextInt(5);
                    while(result[0]==0||result[1]==0||result[2]==0||result[3]==0||result[4]==0||result[5]==0||result[6]==0||result[7]==0)
                    {
                        result[0]=r.nextInt(6);result[1]=r.nextInt(8);
                        result[2]=r.nextInt(8);result[3]=r.nextInt(8);
                        result[4]=r.nextInt(8);result[5]=r.nextInt(5);
                        result[6]=r.nextInt(5);result[7]=r.nextInt(5);
                    }
                    nchar[j][0]=result[0];
                    //System.out.println("player_di = 1, have char :"+nchar[j][0]);
                    label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+nchar[j][0]+".png")));
                    dba.setSql("INSERT INTO party"
                            + "(Char_id, User_id, char_name, Public_slot, level_user, Skill_1, Skill_2, Skill_3, UpgradeSkill_1, UpgradeSkill_2, UpgradeSkill_3) VALUES "
                            //+ "("+nchar[j][0]+","+UID+",'"+getAlphaNumericString(10)+"',0,"+result[1]+","+result[2]+","+result[3]+","+result[4]+","+result[5]+","+result[6]+","+result[7]+")");
                            + "("+nchar[j][0]+","+UID+",'"+getAlphaNumericString(10)+"',0,1,1,1,1,1,1,1)");
                    dba.write();
                    x++;
                } catch (SQLException ex) {
                    Logger.getLogger(scoutPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else
        {
            JOptionPane.showMessageDialog(this,"Haven't enough of gold");
        }
        //JOptionPane.showMessageDialog(this,"Haven't enough of gold");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        img_5 = new javax.swing.JLabel();
        img_1 = new javax.swing.JLabel();
        img_4 = new javax.swing.JLabel();
        img_3 = new javax.swing.JLabel();
        img_2 = new javax.swing.JLabel();
        gold = new javax.swing.JProgressBar();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        username_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usrlvl_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        charcount_label = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("1 x SCOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("5 x SCOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        img_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_5.setText("jLabel1");
        img_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_5.setPreferredSize(new java.awt.Dimension(100, 100));

        img_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_1.setText("jLabel1");
        img_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_1.setPreferredSize(new java.awt.Dimension(100, 100));

        img_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_4.setText("jLabel1");
        img_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_4.setPreferredSize(new java.awt.Dimension(100, 100));

        img_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_3.setText("jLabel1");
        img_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_3.setPreferredSize(new java.awt.Dimension(100, 100));

        img_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_2.setText("jLabel1");
        img_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_2.setPreferredSize(new java.awt.Dimension(100, 100));

        gold.setFont(new java.awt.Font("MV Boli", 1, 8)); // NOI18N
        gold.setString("0 Gold");
        gold.setStringPainted(true);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setText("Name :");

        username_label.setText("XX1");

        jLabel3.setText("User Lvl :");

        usrlvl_label.setText("0");

        jLabel5.setText("Character Count :");

        jButton3.setText("<--");

        charcount_label.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(img_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(img_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(img_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(img_4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(img_5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 102, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username_label)
                .addGap(53, 53, 53)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usrlvl_label)
                .addGap(52, 52, 52)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(charcount_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gold, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gold, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(username_label)
                            .addComponent(jLabel3)
                            .addComponent(usrlvl_label)
                            .addComponent(jLabel5)
                            .addComponent(charcount_label)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(img_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(img_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        scout(1,img_1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        scout(1,img_1);
        scout(1,img_2);
        scout(1,img_3);
        scout(1,img_4);
        scout(1,img_5);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel charcount_label;
    private javax.swing.JProgressBar gold;
    private javax.swing.JLabel img_1;
    private javax.swing.JLabel img_2;
    private javax.swing.JLabel img_3;
    private javax.swing.JLabel img_4;
    private javax.swing.JLabel img_5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel username_label;
    private javax.swing.JLabel usrlvl_label;
    // End of variables declaration//GEN-END:variables
}
