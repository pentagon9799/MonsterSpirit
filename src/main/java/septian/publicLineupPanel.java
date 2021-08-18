/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package septian;

import hilmi.*;
import iskandar.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import hilmi.BattleMainFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class publicLineupPanel extends javax.swing.JPanel {
private String[] PID = {"", "", ""};
private String[][] slot ;//={"","",""};
private String uid="1";
private BattleMainFrame mf;
private Party_Data p1 ,p2,p3;//= new CharPane();
private MainMenu plyr;
private boolean status=false;

//public CharPane p2 = new CharPane();
//public CharPane p3 = new CharPane();
//private String[][] partyslot = new String[3][11];//[id_party][additional atribute]
private hilmi.DBAksess dba = new hilmi.DBAksess();

//    public String[][] getPartyslot() {
//        return partyslot;
//    }
//private Object[] panel= new Object[3];
    /**
     * Creates new form SelectOpponent
     */
    public publicLineupPanel(String uid, MainMenu plyr) {
        initComponents();
        this.plyr=plyr;
        this.uid = uid;
        p1 = new Party_Data(this, 0,uid);
        p2 = new Party_Data(this, 1,uid);
        p3 = new Party_Data(this, 2,uid);
        
        if(dba.checkPartyAvailable(this.uid,1)){
            p1.setParty(loadparty(), 0);
        }
        if(dba.checkPartyAvailable(this.uid,2)){
            p2.setParty(loadparty(), 1);
        }
        if(dba.checkPartyAvailable(this.uid,3)){
            p3.setParty(loadparty(), 2);
        }
        
        add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));
        add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

    }
    
    public publicLineupPanel(BattleMainFrame mf) {
        p1 = new Party_Data(this, 0,mf.getPlayer().getId());
        p2 = new Party_Data(this, 1,mf.getPlayer().getId());
        p3 = new Party_Data(this, 2,mf.getPlayer().getId());

        add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));
        add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));
    }
    public String[][] loadparty()
    {
        String[][] party_data = new String[3][24];//[dba.rowCount("select * from list_party where user_id="+this.uid)];
        int i =0;
        dba.setSql("select * from list_party where user_id="+this.uid+" AND public_slot != 0 ORDER BY Public_slot ASC limit 3");
        //dba.setSql("select * from list_party where user_id="+this.uid+" AND public_slot != 0 limit 3");
        try
        {
            dba.readandShow();
            while(dba.getRs().next())
            {
                party_data[i][0]=dba.getRs().getString(1);
                party_data[i][1]=dba.getRs().getString(2);
                party_data[i][2]=dba.getRs().getString(3);
                party_data[i][3]=dba.getRs().getString(4);
                party_data[i][4]=dba.getRs().getString(5);
                party_data[i][5]=dba.getRs().getString(6);
                party_data[i][6]=dba.getRs().getString(7);
                party_data[i][7]=dba.getRs().getString(8);
                party_data[i][8]=dba.getRs().getString(9);
                party_data[i][9]=dba.getRs().getString(10);
                party_data[i][10]=dba.getRs().getString(11);
                party_data[i][11]=dba.getRs().getString(12);
                party_data[i][12]=dba.getRs().getString(13);
                party_data[i][13]=dba.getRs().getString(14);
                party_data[i][14]=dba.getRs().getString(15);
                party_data[i][15]=dba.getRs().getString(16);
                party_data[i][16]=dba.getRs().getString(17);
                party_data[i][17]=dba.getRs().getString(18);
                party_data[i][18]=dba.getRs().getString(19);
                party_data[i][19]=dba.getRs().getString(20);
                party_data[i][20]=dba.getRs().getString(21);
                party_data[i][21]=dba.getRs().getString(22);
                party_data[i][22]=dba.getRs().getString(23);
                party_data[i][23]=dba.getRs().getString(24);
                //party_data[i][24]=dba.getRs().getString(25);
                i++;
            }
            dba.getCon().close();
        }catch(SQLException ex)
        {
            Logger.getLogger(publicLineupPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.slot=party_data;
        return party_data;
    }
    public boolean setPartyID(int slot, String id) {
        if (PID[0].equals(id) || PID[1].equals(id) || PID[2].equals(id)) 
        {
            return false;
        }
        PID[slot] = id;
        return true;
    }
    public void resetSlot(int slot) 
    {
        PID[slot] = ""; 
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(955, 515));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Set");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 113, 27));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/2@300px.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 430, 430));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 10, 430));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 960, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DASHBOARD LINE UP");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    public void set(String partyid, int slot)
    {
        dba.setSql("UPDATE party SET Public_slot = "+slot+" WHERE user_id = "+uid+" AND party_id ="+partyid+"");      
        try{
            dba.write();
        } catch (SQLException ex) {
            Logger.getLogger(publicLineupPanel.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!PID[0].equals("") || !PID[1].equals("") || !PID[2].equals("")) {
            if (!PID[0].equals("") && !PID[1].equals("") && !PID[2].equals("")) {
            set(PID[0].toString(), 1);
            set(PID[1].toString(), 2);
            set(PID[2].toString(), 3);
            System.out.println("slot 1 : "+PID[0].toString());
            System.out.println("slot 2 : "+PID[1].toString());
            System.out.println("slot 3 : "+PID[2].toString());
            }else
            {
                JOptionPane.showMessageDialog(this, "Please fill all of your party!");
                return;
            }
        }
        if (PID[0].equals("") && PID[1].equals("") && PID[2].equals("")) {
            
        }
//        if (!PID[0].equals("") || !PID[1].equals("") || !PID[2].equals("")) {
//            JOptionPane.showMessageDialog(this, "Please fill all of your party!");
//            return;
//        }
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
