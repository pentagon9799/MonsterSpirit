/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package septian;

import com.ish.monsterspirit.ReadTable;
import iskandar.*;
import hilmi.DBAksess;
import java.awt.Color;
import java.awt.Font;
import static java.awt.image.ImageObserver.ERROR;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class upgradePartyPanel extends javax.swing.JPanel {

    /**
     * Creates new form scoutPanel
     */
    public upgradePartyPanel() {
        initComponents();
        this.coinValue_label.setText(String.valueOf(money));
        v.display_table(TabelGambarCharacter,"select * from list_party2 where user_id = "+String.valueOf(UID));
        testing[0]=TabelGambarCharacter.getRowCount()-1;
        setatribute(testing);
    }
    public upgradePartyPanel(int Uid, int money, MainMenu plyr) {
        initComponents();
        this.plyr=plyr;
        this.UID=Uid;
        this.money=money;
        //money("select * from MainMenu where id="+this.UID+"",money);
        this.coinValue_label.setText(String.valueOf(money));
        v.display_table(TabelGambarCharacter,"select * from list_party2 where user_id = "+String.valueOf(UID));
        testing[0]=TabelGambarCharacter.getRowCount()-1;
        setatribute(testing);
    }
    private int UID=1;
    private int[] testing = new int[1];
    
    private final Integer maxMoney = 10000000; 
    private Integer money =0;
    private Integer reqvalue=4000;
    private ReadTable v = new ReadTable();
    private DBAksess dba = new DBAksess();
    public Object[][] selectedParty;
    private int PID=0;
    private MainMenu plyr;
    
    public int money(String sqlstatment)
    {
     int money_=0;
     dba.setSql(sqlstatment);
     try{
         dba.readandShow();
         while(dba.getRs().next()){
             money_ = dba.getRs().getInt("money");
         }
         dba.getCon().close();
     }
     catch(SQLException ex)
     {
         Logger.getLogger(scoutPanel.class.getName()).log(Level.SEVERE, null, ex);
     }
     return money_;
    }
    public void loadpanel()
    {
        v.display_table(TabelGambarCharacter,"select * from list_party2 where user_id = "+String.valueOf(UID));
        setatribute(testing);
    }
    
    private Integer reqlvlup(Integer lvl, Integer reqvalue)
    {
        reqvalue=((((lvl+1)*1000)+reqvalue)*(lvl+1)-(lvl*1000));
        return reqvalue;
    }
    private void setatribute(int[] selected)
    {
        int x[] = selected;//TabelGambarCharacter.getSelectedRows();
        selectedParty = v.getSelectedParty();
        for(int i=0;i<x.length;i++)
        {
        PID=x[i];
        name_field.setText(v.getSelectedParty()[x[i]][2].toString());    
        int monsterLvl = Integer.parseInt(v.getSelectedParty()[x[i]][9].toString());
        int baseAtk = Integer.parseInt(v.getSelectedParty()[x[i]][5].toString());
        int baseDef = Integer.parseInt(v.getSelectedParty()[x[i]][6].toString());
        int atk = baseAtk + (baseAtk * (monsterLvl/2));
        int def = baseDef + (baseDef * (monsterLvl/2));
        
        int monsterID = Integer.parseInt(v.getSelectedParty()[x[i]][3].toString());
        
        lvl_label.setText(Integer.toString(monsterLvl));
        atk_label.setText(Integer.toString(atk));
        def_label.setText(Integer.toString(def));
        
        skillname1_label.setText(v.getSelectedParty()[x[i]][11].toString());
        skill1_label.setText(v.getSelectedParty()[x[i]][13]+" (+"+v.getSelectedParty()[x[i]][14]+")");//(TabelGambarCharacter.getValueAt(selRow, 10).toString());
        //s1LvlLabel.setText(TabelGambarCharacter.getValueAt(selRow, 13).toString());
        
        skillname2_label.setText(v.getSelectedParty()[x[i]][16].toString());
        skill2_label.setText(v.getSelectedParty()[x[i]][18]+" (+"+v.getSelectedParty()[x[i]][19]+")");//(TabelGambarCharacter.getValueAt(selRow, 15).toString());
        //s2LvlLabel.setText(TabelGambarCharacter.getValueAt(selRow, 18).toString());
        
        skillname3_label.setText(v.getSelectedParty()[x[i]][21].toString());
        skill3_label.setText(v.getSelectedParty()[x[i]][23]+" (+"+v.getSelectedParty()[x[i]][24]+")");//(TabelGambarCharacter.getValueAt(selRow, 20).toString());
        //s3LvlLabel.setText(TabelGambarCharacter.getValueAt(selRow, 23).toString());
        
        Image_monster.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/"+monsterID+".png")));
            System.out.println(v.getSelectedParty()[x[i]][4]);
        }
        reqvalue_label.setText(String.valueOf(reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue)));
    }
    private void upgrade(String skill)
    {
        //if(money>=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))
        if(money("select money from player where user_id = "+this.UID)>=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))
        {
            try{
                //money -=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue);
                dba.setSql("UPDATE player SET money = money - "+(reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))+" WHERE user_id = "+this.UID);
                dba.write();
                //this.coinValue_label.setText(String.valueOf(money));
                dba.setSql("UPDATE party SET UpgradeSkill_"+skill+" = UpgradeSkill_"+skill+" + 1 WHERE user_id = "+String.valueOf(UID)+" AND party_id ="+selectedParty[PID][0]+"");
                dba.write();
                plyr.setdb();
                loadpanel();
            }catch(SQLException ex)
            {
                Logger.getLogger(upgradePartyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            JOptionPane.showMessageDialog(this,"not enough gold");
        }
        //loadpanel();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelGambarCharacter = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel3 = new javax.swing.JPanel();
        lvl_label = new javax.swing.JLabel();
        atk_label = new javax.swing.JLabel();
        def_label = new javax.swing.JLabel();
        skill1_label = new javax.swing.JLabel();
        skill2_label = new javax.swing.JLabel();
        skill3_label = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        skillname1_label = new javax.swing.JLabel();
        skillname2_label = new javax.swing.JLabel();
        skillname3_label = new javax.swing.JLabel();
        Image_monster = new javax.swing.JLabel();
        LvlupBtn = new javax.swing.JButton();
        UpgradeBtn = new javax.swing.JButton();
        UpgradeBtn1 = new javax.swing.JButton();
        UpgradeBtn2 = new javax.swing.JButton();
        coinIcon_label = new javax.swing.JLabel();
        coinValue_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        reqvalue_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(955, 515));
        setPreferredSize(new java.awt.Dimension(955, 515));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        TabelGambarCharacter.setBackground(new java.awt.Color(0, 0, 0));
        TabelGambarCharacter.setForeground(new java.awt.Color(255, 255, 255));
        TabelGambarCharacter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelGambarCharacter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelGambarCharacterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelGambarCharacter);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 66, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lvl_label.setBackground(new java.awt.Color(204, 204, 204));
        lvl_label.setForeground(new java.awt.Color(255, 255, 255));
        lvl_label.setText("Lvl :");
        lvl_label.setPreferredSize(new java.awt.Dimension(35, 34));

        atk_label.setBackground(new java.awt.Color(204, 204, 204));
        atk_label.setForeground(new java.awt.Color(255, 255, 255));
        atk_label.setText("ATK :");
        atk_label.setPreferredSize(new java.awt.Dimension(35, 34));

        def_label.setBackground(new java.awt.Color(204, 204, 204));
        def_label.setForeground(new java.awt.Color(255, 255, 255));
        def_label.setText("DEF :");
        def_label.setPreferredSize(new java.awt.Dimension(35, 34));

        skill1_label.setBackground(new java.awt.Color(204, 204, 204));
        skill1_label.setForeground(new java.awt.Color(255, 255, 255));
        skill1_label.setText("Skill 1 :");
        skill1_label.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        skill1_label.setPreferredSize(new java.awt.Dimension(35, 34));

        skill2_label.setBackground(new java.awt.Color(204, 204, 204));
        skill2_label.setForeground(new java.awt.Color(255, 255, 255));
        skill2_label.setText("Skill 2 :");
        skill2_label.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        skill2_label.setPreferredSize(new java.awt.Dimension(35, 34));

        skill3_label.setBackground(new java.awt.Color(204, 204, 204));
        skill3_label.setForeground(new java.awt.Color(255, 255, 255));
        skill3_label.setText("Skill 3 :");
        skill3_label.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ability", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        skill3_label.setPreferredSize(new java.awt.Dimension(35, 34));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ATK :");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lvl     :");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Name :");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DEF :");

        skillname1_label.setForeground(new java.awt.Color(255, 255, 255));
        skillname1_label.setText("Skill 1 :");

        skillname2_label.setForeground(new java.awt.Color(255, 255, 255));
        skillname2_label.setText("Skill 2 :");

        skillname3_label.setForeground(new java.awt.Color(255, 255, 255));
        skillname3_label.setText("Skill 3 :");

        Image_monster.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Image_monster.setPreferredSize(new java.awt.Dimension(100, 100));

        LvlupBtn.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        LvlupBtn.setText("UP");
        LvlupBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        LvlupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LvlupBtnActionPerformed(evt);
            }
        });

        UpgradeBtn.setText("+");
        UpgradeBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        UpgradeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpgradeBtnActionPerformed(evt);
            }
        });

        UpgradeBtn1.setText("+");
        UpgradeBtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        UpgradeBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpgradeBtn1ActionPerformed(evt);
            }
        });

        UpgradeBtn2.setText("+");
        UpgradeBtn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        UpgradeBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpgradeBtn2ActionPerformed(evt);
            }
        });

        coinIcon_label.setForeground(new java.awt.Color(255, 255, 255));
        coinIcon_label.setText("coinIcon");

        coinValue_label.setForeground(new java.awt.Color(255, 255, 255));
        coinValue_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        coinValue_label.setText("0");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Money lvl UP :");

        reqvalue_label.setForeground(new java.awt.Color(255, 255, 255));
        reqvalue_label.setText("0");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(":");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText(":");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText(":");

        name_field.setBackground(new java.awt.Color(0, 0, 0));
        name_field.setForeground(new java.awt.Color(255, 255, 255));
        name_field.setText("name");
        name_field.setBorder(null);
        name_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                name_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                name_fieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lvl_label, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(coinValue_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Image_monster, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(coinIcon_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LvlupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(3, 3, 3)
                        .addComponent(atk_label, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(def_label, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reqvalue_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(skillname1_label, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(skillname2_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(skillname3_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(skill3_label, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpgradeBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(skill2_label, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpgradeBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(skill1_label, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpgradeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addContainerGap())
            .addComponent(jSeparator3)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(coinValue_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coinIcon_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LvlupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Image_monster, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lvl_label, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(atk_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(def_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel13))
                    .addComponent(reqvalue_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpgradeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(skill1_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(skillname1_label)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpgradeBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(skill2_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(skillname2_label)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpgradeBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(skill3_label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(skillname3_label)
                        .addComponent(jLabel4)))
                .addGap(71, 71, 71))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 66, 370, 370));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 8));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 51, 974, -1));

        jSeparator4.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 66, 11, 371));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DASHBOARD PARTY");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 2, -1, 40));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/4@300px.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 520));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void LvlupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LvlupBtnActionPerformed
        System.out.println(reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue)+" Money :"+money);
        //if(money>=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))
        if(money("select money from player where user_id = "+this.UID)>=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))
        {
            try {
                dba.setSql("UPDATE player SET money = money - "+(reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue))+" WHERE user_id = "+this.UID);
                dba.write();
//                money -=reqlvlup(Integer.parseInt(lvl_label.getText()),reqvalue);
//                //this.gold.setString(money+"/"+maxMoney+" Gold");//(currentHP+"/"+maximumHP+" HP");
//                //this.gold.setValue(money);
                this.coinValue_label.setText(String.valueOf(money));
                dba.setSql("UPDATE party SET level_user = level_user + 1 WHERE user_id = "+String.valueOf(UID)+" AND party_id ="+selectedParty[PID][0]+"");
                dba.write();
                plyr.setdb();
                JOptionPane.showMessageDialog(this,"mosnter upgraded to Lvl : "+(Integer.parseInt(lvl_label.getText())+1));
                loadpanel();
            } catch (SQLException ex) {
                Logger.getLogger(upgradePartyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            JOptionPane.showMessageDialog(this,"not enough gold");
        }
    }//GEN-LAST:event_LvlupBtnActionPerformed

    private void TabelGambarCharacterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelGambarCharacterMouseClicked
        testing=TabelGambarCharacter.getSelectedRows();
        setatribute(TabelGambarCharacter.getSelectedRows());
    }//GEN-LAST:event_TabelGambarCharacterMouseClicked

    private void UpgradeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpgradeBtnActionPerformed
        upgrade("1");
    }//GEN-LAST:event_UpgradeBtnActionPerformed

    private void UpgradeBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpgradeBtn1ActionPerformed
        upgrade("2");
    }//GEN-LAST:event_UpgradeBtn1ActionPerformed

    private void UpgradeBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpgradeBtn2ActionPerformed
        upgrade("3");
    }//GEN-LAST:event_UpgradeBtn2ActionPerformed

    private void name_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_fieldFocusLost
        if(!name_field.getText().equals("")){
            try {
                dba.setSql("UPDATE party SET char_name = '"+name_field.getText()+"' WHERE user_id = "+String.valueOf(UID)+" AND party_id ="+selectedParty[PID][0]+"");      
                dba.write();
                loadpanel();
            } catch (SQLException ex) {
                Logger.getLogger(upgradePartyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_name_fieldFocusLost

    private void name_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_name_fieldFocusGained
        if(!name_field.getText().equals("")){
           name_field.setText("");      
        }
    }//GEN-LAST:event_name_fieldFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image_monster;
    private javax.swing.JButton LvlupBtn;
    private javax.swing.JTable TabelGambarCharacter;
    private javax.swing.JButton UpgradeBtn;
    private javax.swing.JButton UpgradeBtn1;
    private javax.swing.JButton UpgradeBtn2;
    private javax.swing.JLabel atk_label;
    private javax.swing.JLabel coinIcon_label;
    private javax.swing.JLabel coinValue_label;
    private javax.swing.JLabel def_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lvl_label;
    private javax.swing.JTextField name_field;
    private javax.swing.JLabel reqvalue_label;
    private javax.swing.JLabel skill1_label;
    private javax.swing.JLabel skill2_label;
    private javax.swing.JLabel skill3_label;
    private javax.swing.JLabel skillname1_label;
    private javax.swing.JLabel skillname2_label;
    private javax.swing.JLabel skillname3_label;
    // End of variables declaration//GEN-END:variables
}
