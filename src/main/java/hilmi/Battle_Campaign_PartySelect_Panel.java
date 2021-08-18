/*
 * Hilmi Abdurrahman Fakhrudin - 1807422008
 */
package hilmi;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hilmi
 */
public class Battle_Campaign_PartySelect_Panel extends javax.swing.JPanel {

    private Party_Data p1, p2, p3;
    private String[] pID = {"", "", ""};
    private int lvl, backBtnAction = 0;
    private BattleMainFrame mf;
    private boolean globalVS = false;
    private String[] opponentData;
    private String[][] opponentLineUp;

    private String[][] lvl_1_Enemy = {
        {"2", "Monster A", "12", "7", ""},
        {"3", "Monster B", "9", "9", ""},
        {"2", "Monster C", "15", "12", ""},};

    private String[][] lvl_2_Enemy = {
        {"4", "The X", "18", "12", ""},
        {"1", "The Y", "15", "15", ""},
        {"5", "The Z", "20", "14", ""},};

    private String[][] lvl_3_Enemy = {
        {"1", "Monster A", "12", "18", ""},
        {"3", "Monster B", "9", "17", ""},
        {"4", "Monster C", "15", "19", ""},};

    private String[][] lvl_4_Enemy = {
        {"5", "Bossy", "50", "50", ""},};

    /**
     * Creates new form Battle_Campaign_PartySelect_Panel
     *
     * @param mf Frame utama
     * @param lvl lvl Campaign
     * @param opponentData Data User lawan
     * @param opponentLineUp data monster lawan
     */
    public Battle_Campaign_PartySelect_Panel(BattleMainFrame mf, int lvl, String[] opponentData, String[][] opponentLineUp) {
        initComponents();
        this.mf = mf;
        this.lvl = lvl;
        
        RewardMoney.setText("1000");
        
        if (lvl == -1) {
            backBtnAction = 1;
            globalVS = true;
            this.opponentData = opponentData;
            this.opponentLineUp = opponentLineUp;
            RewardMoney.setText("3000");

            opponentName.setText(this.opponentData[13]);
            header.setText("Quest Detail - Online");

            javax.swing.JLabel[] monIcon = {enemyIcon1, enemyIcon2, enemyIcon3};
            javax.swing.JLabel[] monLabel = {enemyName1, enemyName2, enemyName3};

            for (int i = 0; i < monIcon.length; i++) {
                monIcon[i].setVisible(false);
                monLabel[i].setVisible(false);
            }

            for (int i = 0; i < opponentLineUp.length; i++) {
                monIcon[i].setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + opponentLineUp[i][3] + ".png")));
                monLabel[i].setText(opponentLineUp[i][2]);
                monIcon[i].setVisible(true);
                monLabel[i].setVisible(true);
            }
        } else {
            header.setText("Quest Detail - Lvl. " + lvl);
            switch (lvl) {
                case 1:
                    enemyIcon1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_1_Enemy[0][0] + ".png")));
                    enemyIcon2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_1_Enemy[1][0] + ".png")));
                    enemyIcon3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_1_Enemy[2][0] + ".png")));

                    enemyName1.setText(lvl_1_Enemy[0][1]);
                    enemyName2.setText(lvl_1_Enemy[1][1]);
                    enemyName3.setText(lvl_1_Enemy[2][1]);
                    RewardMoney.setText("500");
                    break;
                case 2:
                    enemyIcon1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_2_Enemy[0][0] + ".png")));
                    enemyIcon2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_2_Enemy[1][0] + ".png")));
                    enemyIcon3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_2_Enemy[2][0] + ".png")));

                    enemyName1.setText(lvl_2_Enemy[0][1]);
                    enemyName2.setText(lvl_2_Enemy[1][1]);
                    enemyName3.setText(lvl_2_Enemy[2][1]);
                    RewardMoney.setText("750");
                    break;
                case 3:
                    enemyIcon1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_3_Enemy[0][0] + ".png")));
                    enemyIcon2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_3_Enemy[1][0] + ".png")));
                    enemyIcon3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_3_Enemy[2][0] + ".png")));

                    enemyName1.setText(lvl_3_Enemy[0][1]);
                    enemyName2.setText(lvl_3_Enemy[1][1]);
                    enemyName3.setText(lvl_3_Enemy[2][1]);
                    RewardMoney.setText("1000");
                    break;
                case 4:
                    enemyIcon1.setVisible(false);
                    enemyIcon2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + lvl_4_Enemy[0][0] + ".png")));
                    enemyIcon3.setVisible(false);

                    enemyName1.setVisible(false);
                    enemyName2.setText(lvl_4_Enemy[0][1]);
                    enemyName3.setVisible(false);
                    
                    RewardMoney.setText("2000");
                    break;
            }
        }

        
        p1 = new Party_Data(this, 0, mf.getPlayer().getId());
        p2 = new Party_Data(this, 1, mf.getPlayer().getId());
        p3 = new Party_Data(this, 2, mf.getPlayer().getId());

        add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));
        add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));
        add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));
    }

    public boolean setPartyID(int slot, String id) {
        if (pID[0].equals(id) || pID[1].equals(id) || pID[2].equals(id)) {
            return false;
        }
        pID[slot] = id;
        return true;
    }

    public void resetSlot(int slot) {
        pID[slot] = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        header = new javax.swing.JLabel();
        battleBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        RewardMoney = new javax.swing.JLabel();
        enemyIcon3 = new javax.swing.JLabel();
        enemyIcon1 = new javax.swing.JLabel();
        enemyIcon2 = new javax.swing.JLabel();
        enemyName3 = new javax.swing.JLabel();
        enemyName1 = new javax.swing.JLabel();
        enemyName2 = new javax.swing.JLabel();
        opponentSeparator = new javax.swing.JSeparator();
        opponentName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(848, 480));
        setMinimumSize(new java.awt.Dimension(848, 480));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 80, 50));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 10, 290));

        header.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Quest Detail");
        add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 610, -1));

        battleBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        battleBtn.setText("BATTLE!");
        battleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                battleBtnActionPerformed(evt);
            }
        });
        add(battleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 270, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enemy:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Battle Reward:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, -1, -1));

        RewardMoney.setForeground(new java.awt.Color(255, 255, 255));
        RewardMoney.setText("1000");
        add(RewardMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 360, -1, -1));

        enemyIcon3.setBackground(new java.awt.Color(255, 255, 255));
        enemyIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyIcon3.setMaximumSize(new java.awt.Dimension(75, 75));
        enemyIcon3.setMinimumSize(new java.awt.Dimension(75, 75));
        enemyIcon3.setName(""); // NOI18N
        enemyIcon3.setOpaque(true);
        enemyIcon3.setPreferredSize(new java.awt.Dimension(75, 75));
        add(enemyIcon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 220, -1, -1));

        enemyIcon1.setBackground(new java.awt.Color(255, 255, 255));
        enemyIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyIcon1.setMaximumSize(new java.awt.Dimension(75, 75));
        enemyIcon1.setMinimumSize(new java.awt.Dimension(75, 75));
        enemyIcon1.setName(""); // NOI18N
        enemyIcon1.setOpaque(true);
        enemyIcon1.setPreferredSize(new java.awt.Dimension(75, 75));
        add(enemyIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, -1));

        enemyIcon2.setBackground(new java.awt.Color(255, 255, 255));
        enemyIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyIcon2.setMaximumSize(new java.awt.Dimension(75, 75));
        enemyIcon2.setMinimumSize(new java.awt.Dimension(75, 75));
        enemyIcon2.setName(""); // NOI18N
        enemyIcon2.setOpaque(true);
        enemyIcon2.setPreferredSize(new java.awt.Dimension(75, 75));
        add(enemyIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, -1, -1));

        enemyName3.setForeground(new java.awt.Color(255, 255, 255));
        enemyName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyName3.setText("jLabel4");
        add(enemyName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 80, -1));

        enemyName1.setForeground(new java.awt.Color(255, 255, 255));
        enemyName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyName1.setText("jLabel4");
        add(enemyName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 80, -1));

        enemyName2.setForeground(new java.awt.Color(255, 255, 255));
        enemyName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyName2.setText("jLabel4");
        add(enemyName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 80, -1));
        add(opponentSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 270, 10));

        opponentName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        opponentName.setForeground(new java.awt.Color(255, 255, 255));
        opponentName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opponentName.setText("This Level Enemy");
        add(opponentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 270, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/3@300px.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 830, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (backBtnAction == 1) {
            mf.runOnlineBattleMatchMaking();
        } else {
            mf.runCampaignLvlSelect();
        }
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void battleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_battleBtnActionPerformed
        if (pID[0].equals("") || pID[1].equals("") || pID[2].equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill all of your party!");
            return;
        }
        String[][] partyData = {
            p1.getDataMonster(),
            p2.getDataMonster(),
            p3.getDataMonster()
        };
        if (!globalVS) {
            switch (lvl) {
                case 1:
                    mf.runBattleAction(new Battle_Campaign_Action_Normal(mf, lvl, partyData, lvl_1_Enemy, Integer.parseInt(RewardMoney.getText())));
                    this.setVisible(false);
                    break;
                case 2:
                    mf.runBattleAction(new Battle_Campaign_Action_Normal(mf, lvl, partyData, lvl_2_Enemy, Integer.parseInt(RewardMoney.getText())));
                    this.setVisible(false);
                    break;
                case 3:
                    mf.runBattleAction(new Battle_Campaign_Action_Normal(mf, lvl, partyData, lvl_3_Enemy, Integer.parseInt(RewardMoney.getText())));
                    this.setVisible(false);
                    break;
                case 4:
                    mf.runBattleAction(new Battle_Campaign_Action_Normal(mf, lvl, partyData, lvl_4_Enemy, Integer.parseInt(RewardMoney.getText())));
                    this.setVisible(false);
                    break;
            }
            return;
        } else {
            mf.runBattleAction(new Battle_Campaign_Action_Normal(mf, -1, partyData, opponentLineUp, Integer.parseInt(RewardMoney.getText())));
            this.setVisible(false);
        }
    }//GEN-LAST:event_battleBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RewardMoney;
    private javax.swing.JButton backButton;
    private javax.swing.JButton battleBtn;
    private javax.swing.JLabel enemyIcon1;
    private javax.swing.JLabel enemyIcon2;
    private javax.swing.JLabel enemyIcon3;
    private javax.swing.JLabel enemyName1;
    private javax.swing.JLabel enemyName2;
    private javax.swing.JLabel enemyName3;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel opponentName;
    private javax.swing.JSeparator opponentSeparator;
    // End of variables declaration//GEN-END:variables
}
