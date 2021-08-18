/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package septian;

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
import iskandar.*;
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
    private int UID = 0;
    private int maxMoney = 1000000000;
    private int money = 0;
    private int reqvalue = 9000;
    private DBAksess dba = new DBAksess();
    private MainMenu plyr;
    private boolean newPlayer = false;
    private NewPlayerScout nps;
    //private boolean status;

    // Color color ;//= new Color(150, 255, 243);
    public scoutPanel() {
        initComponents();
        //coinValue_label.setText(String.valueOf(money));
        setImage("noIcon");
        newPlayerLabel.setVisible(false);
    }

    public scoutPanel(int uid, NewPlayerScout nps) {
        initComponents();
        this.nps = nps;
        this.UID = uid;
        setImage("noIcon");
        newPlayer = true;
        jButton1.setVisible(false);
    }

    public scoutPanel(int uid/*,int money*/, MainMenu plyr) {
        initComponents();
        this.plyr = plyr;
        this.UID = uid;
//        this.money=money;
//        //this.money=money("select money from MainMenu where id="+this.UID+"",this.money);
//        //money("select * from MainMenu where id="+this.UID+"",money);
//        //System.out.println("ini duit : "+money);
        //coinValue_label.setText(String.valueOf(this.money));
        setImage("noIcon");
        newPlayerLabel.setVisible(false);
    }

    public int money(String sqlstatment) {
        int money_ = 0;
        dba.setSql(sqlstatment);
        try {
            dba.readandShow();
            while (dba.getRs().next()) {
                money_ = dba.getRs().getInt("money");//.getInt("money");
            }
            dba.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(scoutPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return money_;
    }

    private static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    private void setImage(String gainedchar) {
        img_1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + gainedchar + ".png")));
        img_1.setBorder(new LineBorder(Color.RED, 5));
        charName1_label.setText("");
        img_2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + gainedchar + ".png")));
        img_2.setBorder(new LineBorder(Color.RED, 5));
        charName2_label.setText("");
        img_3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + gainedchar + ".png")));
        img_3.setBorder(new LineBorder(Color.RED, 5));
        charName3_label.setText("");
        img_4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + gainedchar + ".png")));
        img_4.setBorder(new LineBorder(Color.RED, 5));
        charName4_label.setText("");
        img_5.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + gainedchar + ".png")));
        img_5.setBorder(new LineBorder(Color.RED, 5));
        charName5_label.setText("");
    }

    private void scout(int count, JLabel label_image, JLabel charName_label) {
        try {
            //if(money("select money from MainMenu where user_id = "+this.UID)>=(reqvalue*count)){
            dba.setSql("UPDATE player SET money = money - " + (reqvalue * count) + " WHERE user_id = " + this.UID);
            dba.write();
            nchar = new int[count][1];
            int x = 0;
            for (int j = 0; j < count; j++) {
                //int result = r.nextInt(6);
                int[] result = new int[8];
                result[0] = r.nextInt(6);
                result[1] = r.nextInt(8);
                result[2] = r.nextInt(8);
                result[3] = r.nextInt(8);
                result[4] = r.nextInt(8);
                result[5] = r.nextInt(5);
                result[6] = r.nextInt(5);
                result[7] = r.nextInt(5);
                while (result[0] == 0 || result[1] == 0 || result[2] == 0 || result[3] == 0 || result[4] == 0 || result[5] == 0 || result[6] == 0 || result[7] == 0) {
                    result[0] = r.nextInt(6);
                    result[1] = r.nextInt(8);
                    result[2] = r.nextInt(8);
                    result[3] = r.nextInt(8);
                    result[4] = r.nextInt(8);
                    result[5] = r.nextInt(5);
                    result[6] = r.nextInt(5);
                    result[7] = r.nextInt(5);
                }
                nchar[j][0] = result[0];
                //System.out.println("player_di = 1, have char :"+nchar[j][0]);
                label_image.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + nchar[j][0] + ".png")));
                charName_label.setText("X"+nchar[j][0]);
                dba.setSql("INSERT INTO party"
                        + "(Char_id, User_id, char_name, Public_slot, level_user, Skill_1, Skill_2, Skill_3, UpgradeSkill_1, UpgradeSkill_2, UpgradeSkill_3) VALUES "
                        + "(" + nchar[j][0] + "," + UID + ",'X" + nchar[j][0] + "',0,1," + result[2] + "," + result[3] + "," + result[4] + ",1,1,1)");
                //+ "("+nchar[j][0]+","+UID+",'"+charName_label.getText()+"',0,1,1,1,1,1,1,1)");
                dba.write();
                if (!newPlayer) {
                    plyr.setdb();
                }
                x++;
            }
            //}else
            //{
            //    //JOptionPane.showMessageDialog(this,"Haven't enough of gold");
            //    status=false;
            //}
        } catch (SQLException ex) {
            Logger.getLogger(scoutPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        charName2_label = new javax.swing.JLabel();
        charName1_label = new javax.swing.JLabel();
        charName3_label = new javax.swing.JLabel();
        charName4_label = new javax.swing.JLabel();
        charName5_label = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        newPlayerLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("9000 (1 x SCOUT)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 142, 81));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("45000 (5 x SCOUT)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 380, 142, 81));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 955, 20));

        img_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_5.setPreferredSize(new java.awt.Dimension(100, 100));
        add(img_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 67, -1, -1));

        img_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_1.setPreferredSize(new java.awt.Dimension(100, 100));
        add(img_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 67, -1, -1));

        img_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_4.setPreferredSize(new java.awt.Dimension(100, 100));
        add(img_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 67, -1, -1));

        img_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_3.setPreferredSize(new java.awt.Dimension(100, 100));
        add(img_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 67, -1, -1));

        img_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_2.setPreferredSize(new java.awt.Dimension(100, 100));
        add(img_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 67, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 244, 955, 10));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 295, 50, 220));

        charName2_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        charName2_label.setForeground(new java.awt.Color(255, 255, 255));
        charName2_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charName2_label.setText("jLabel2");
        charName2_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(charName2_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 202, 100, -1));

        charName1_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        charName1_label.setForeground(new java.awt.Color(255, 255, 255));
        charName1_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charName1_label.setText("jLabel2");
        charName1_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(charName1_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 202, 100, -1));

        charName3_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        charName3_label.setForeground(new java.awt.Color(255, 255, 255));
        charName3_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charName3_label.setText("jLabel2");
        charName3_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(charName3_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 202, 100, -1));

        charName4_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        charName4_label.setForeground(new java.awt.Color(255, 255, 255));
        charName4_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charName4_label.setText("jLabel2");
        charName4_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(charName4_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 202, 100, -1));

        charName5_label.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        charName5_label.setForeground(new java.awt.Color(255, 255, 255));
        charName5_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        charName5_label.setText("jLabel2");
        charName5_label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(charName5_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(791, 202, 100, -1));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 67, 17, 113));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 67, 17, 113));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 67, 17, 113));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 67, 17, 113));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 960, 10));

        newPlayerLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        newPlayerLabel.setForeground(new java.awt.Color(255, 255, 255));
        newPlayerLabel.setText("To Start Adventuring, Scout your monster!");
        add(newPlayerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DASHBOARD SCOUT PANEL");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/4@300px.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (money("select money from player where user_id = " + this.UID) >= (reqvalue)) {
            setImage("noIcon");
            scout(1, img_1, charName1_label);
        } else {
            JOptionPane.showMessageDialog(this, "not enough gold");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (money("select money from player where user_id = " + this.UID) >= (reqvalue * 5)) {
            setImage("noIcon");
            scout(1, img_1, charName1_label);
            scout(1, img_2, charName2_label);
            scout(1, img_3, charName3_label);
            scout(1, img_4, charName4_label);
            scout(1, img_5, charName5_label);
            if (newPlayer) {
                JOptionPane.showMessageDialog(this, "Let's Start the adventure!");
                nps.startNewGame();
            }
        } else {
            JOptionPane.showMessageDialog(this, "not enough gold");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel charName1_label;
    private javax.swing.JLabel charName2_label;
    private javax.swing.JLabel charName3_label;
    private javax.swing.JLabel charName4_label;
    private javax.swing.JLabel charName5_label;
    private javax.swing.JLabel img_1;
    private javax.swing.JLabel img_2;
    private javax.swing.JLabel img_3;
    private javax.swing.JLabel img_4;
    private javax.swing.JLabel img_5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel newPlayerLabel;
    // End of variables declaration//GEN-END:variables
}
