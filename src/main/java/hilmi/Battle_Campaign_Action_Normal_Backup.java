/*
 * Hilmi Abdurrahman Fakhrudin - 1807422008
 */
package hilmi;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author hilmi
 */
public class Battle_Campaign_Action_Normal_Backup extends javax.swing.JPanel {

    private int lvl,
            currentParty = 0,
            currentEnemy = 0,
            ownHP = 100,
            enemyHP = 100,
            ownAtk = 0,
            ownDef = 0,
            enemyAtk = 0,
            enemyDef = 0,
            skill1CD = 0,
            skill2CD = 0,
            skill3CD = 0,
            ownDefBuffCD = 0,
            ownDefBuffVal = 0;
    private String[][] enemyData; //id, name, atk, def, status
    private String[][] partyData;
    private boolean globalVS = false, ownDefBuff = false;
    private String enemyName = "", ownName = "";
    private Random rng = new Random();
    private int reward;

    private Monster[] ownMonsterList;
    private Monster[] enemyMonsterList;

    private DefaultCaret caret;

    Battle_Campaign_PartySelect_Panel bcp;
    BattleMainFrame mf;

    /**
     * Creates new form Battle_Campaign_Normal
     */
    public Battle_Campaign_Action_Normal_Backup(BattleMainFrame mf, int lvl, String[][] partyData, String[][] enemyData, int reward) {
        initComponents();

        // Test Monster Class
        ownMonsterList = new Monster[partyData.length];
        enemyMonsterList = new Monster[enemyData.length];

        this.reward = reward;
        caret = (DefaultCaret) battleLogArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.mf = mf;
        this.lvl = lvl;
        this.partyData = partyData;
        this.enemyData = enemyData;
        System.out.println(lvl);
        if (lvl == -1) {
            globalVS = true;
        }

        setOwnMonster(0);
        setEnemyMonster(0);

        battleLogArea.append("Battle Started!\n");
        battleLogArea.append("Your Turn...!\n");
    }

    private void setOwnMonster(int queueNum) {
        ownHP = 100;
        ownName = partyData[queueNum][2];
        ownMonsterName.setText(ownName);
        ownMonsterHealthBar.setValue(ownHP);
        ownMonsterIcon.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + partyData[queueNum][3] + ".png")));
        skill1Btn.setText(partyData[queueNum][10]);
        skill2Btn.setText(partyData[queueNum][15]);
        skill3Btn.setText(partyData[queueNum][20]);
        skill1CD = skill2CD = skill3CD = ownDefBuffCD = 0;;
        checkSkillCD();
        int ownMonsterLevel = Integer.parseInt(partyData[currentParty][8]);
        int ownBaseAtk = Integer.parseInt(partyData[currentParty][5]);
        int ownBaseDef = Integer.parseInt(partyData[currentParty][5]);
        ownAtk = ownBaseAtk + (ownBaseAtk * (ownMonsterLevel / 2));
        ownDef = ownBaseDef + (ownBaseDef * (ownMonsterLevel / 2));
        battleLogArea.append(ownName + " Entered the Field!\n");
    }

    private void setEnemyMonster(int queueNum) {
        enemyHP = 100;
        if (!globalVS) {
            enemyName = enemyData[queueNum][1];
            enemyTitle.setText("Enemy (" + (currentEnemy + 1) + "/" + enemyData.length + ")");
            enemyMonsterName.setText(enemyName);
            enemyMonsterHealthBar.setValue(enemyHP);
            enemyMonsterIcon.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + enemyData[queueNum][0] + ".png")));
            enemyAtk = Integer.parseInt(enemyData[queueNum][2]);
            enemyDef = Integer.parseInt(enemyData[queueNum][3]);
            battleLogArea.append(enemyName + " Entered the Field!\n");
        } else {
            enemyName = enemyData[queueNum][2];
            enemyTitle.setText("Enemy (" + (currentEnemy + 1) + "/" + enemyData.length + ")");
            enemyMonsterName.setText(enemyName);
            enemyMonsterHealthBar.setValue(enemyHP);
            enemyMonsterIcon.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("monsterIcon/" + enemyData[queueNum][3] + ".png")));

            int enemyMonsterLevel = Integer.parseInt(partyData[currentParty][8]);
            int enemyBaseAtk = Integer.parseInt(partyData[currentParty][5]);
            int enemyBaseDef = Integer.parseInt(partyData[currentParty][5]);
            enemyAtk = enemyBaseAtk + (enemyBaseAtk * (enemyMonsterLevel / 2));
            enemyDef = enemyBaseDef + (enemyBaseDef * (enemyMonsterLevel / 2));

            battleLogArea.append(enemyName + " Entered the Field!\n");
        }
    }

    private void checkEnemyHealth() {
        if (enemyHP < 0) {
            enemyHP = 0;
            battleLogArea.append(enemyName + " Defeated!!\n");
            currentEnemy++;
            if (currentEnemy >= enemyData.length) {
                JOptionPane.showMessageDialog(this, "You Win!");
                mf.getMainMenu().setVisible(true);
                mf.getPlayer().addMoney(reward);
                mf.getMainMenu().setdb();
                mf.setVisible(false);
//                this.setVisible(false);
//                mf.runCampaignLvlSelect();
                return;
            }
            setEnemyMonster(currentEnemy);
        } else {
            enemyAtk();
        }
    }

    private void checkOwnHealth() {
        if (ownHP < 0) {
            ownHP = 0;
            battleLogArea.append(ownName + " Defeated!!\n");
            currentParty++;
            if (currentParty >= partyData.length) {
                JOptionPane.showMessageDialog(this, "You Lose!");
                this.setVisible(false);
                mf.runCampaignLvlSelect();
                return;
            }
            setOwnMonster(currentParty);
        }
    }

    private void enemyAtk() {
        battleLogArea.append(enemyName + " Used Normal Attack!\n");
        int currAtk = enemyAtk - ownDef;
        currAtk = (currAtk - 5) - rng.nextInt(20);
        if (currAtk <= 0) {
            currAtk = rng.nextInt(5);
        }
        if (rng.nextInt() > 80) {
            currAtk = currAtk * 2;
            battleLogArea.append("It is Critical Attack!\n");
        }
        ownHP = ownHP - currAtk;
        ownMonsterHealthBar.setValue(ownHP);
        battleLogArea.append(ownName + " Took " + currAtk + " Damages!\n");
        checkOwnHealth();
    }

    private void checkSkillCD() {
        if (skill1CD < 2) {
            skill1CD = 0;
            skill1Btn.setEnabled(true);
            skill1Btn.setText(partyData[currentParty][10]);
        } else {
            skill1CD--;
            skill1Btn.setText(partyData[currentParty][10] + " (" + skill1CD + ")");
        }

        if (skill2CD < 2) {
            skill2CD = 0;
            skill2Btn.setEnabled(true);
            skill2Btn.setText(partyData[currentParty][15]);
        } else {
            skill2CD--;
            skill2Btn.setText(partyData[currentParty][15] + " (" + skill2CD + ")");
        }

        if (skill3CD < 2) {
            skill3CD = 0;
            skill3Btn.setEnabled(true);
            skill3Btn.setText(partyData[currentParty][20]);
        } else {
            skill3CD--;
            skill3Btn.setText(partyData[currentParty][20] + " (" + skill3CD + ")");
        }

        if (ownDefBuffCD < 2) {
            ownDefBuffCD = 0;
            ownDef -= ownDefBuffVal;
            ownDefBuffVal = 0;
        } else {
            ownDefBuffCD--;
        }
    }

    public void useItem(String itemName, String effect, int effectValue) {
        switch (effect) {
            case "heal":
                ownHP += effectValue;
                if (ownHP > 100) {
                    ownHP = 100;
                }
                ownMonsterHealthBar.setValue(ownHP);
                battleLogArea.append(ownName + " Used Potion!\n");
                break;
            case "atk":
                ownAtk += effectValue;
                battleLogArea.append(ownName + " Increased It's Attack!\n");
                break;
            case "def":
                ownDef += effectValue;
                battleLogArea.append(ownName + " Increased It's Defense!\n");
                break;
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

        forfeitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        battleLogArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        skill3Btn = new javax.swing.JButton();
        openItemBtn = new javax.swing.JButton();
        skill1Btn = new javax.swing.JButton();
        normalAtkBtn = new javax.swing.JButton();
        skill2Btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ownMonsterHealthBar = new javax.swing.JProgressBar();
        ownMonsterName = new javax.swing.JLabel();
        enemyTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        enemyMonsterHealthBar = new javax.swing.JProgressBar();
        enemyMonsterName = new javax.swing.JLabel();
        enemyMonsterIcon = new javax.swing.JLabel();
        ownMonsterIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(820, 430));
        setMinimumSize(new java.awt.Dimension(820, 430));
        setPreferredSize(new java.awt.Dimension(820, 430));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        forfeitBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        forfeitBtn.setText("Forfeit");
        forfeitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forfeitBtnActionPerformed(evt);
            }
        });
        add(forfeitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 48));

        battleLogArea.setEditable(false);
        battleLogArea.setBackground(new java.awt.Color(0, 0, 0));
        battleLogArea.setColumns(20);
        battleLogArea.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        battleLogArea.setForeground(new java.awt.Color(255, 255, 255));
        battleLogArea.setRows(5);
        jScrollPane1.setViewportView(battleLogArea);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 350, 70));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Battle Log");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        skill3Btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        skill3Btn.setText("Skill 3");
        skill3Btn.setPreferredSize(new java.awt.Dimension(50, 50));
        skill3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skill3BtnActionPerformed(evt);
            }
        });
        add(skill3Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 120, 30));

        openItemBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        openItemBtn.setText("Item");
        openItemBtn.setPreferredSize(new java.awt.Dimension(50, 50));
        openItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemBtnActionPerformed(evt);
            }
        });
        add(openItemBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 80, 70));

        skill1Btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        skill1Btn.setText("Skill 1");
        skill1Btn.setPreferredSize(new java.awt.Dimension(50, 50));
        skill1Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skill1BtnActionPerformed(evt);
            }
        });
        add(skill1Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 350, 120, 30));

        normalAtkBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        normalAtkBtn.setText("Normal Atk");
        normalAtkBtn.setPreferredSize(new java.awt.Dimension(50, 50));
        normalAtkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalAtkBtnActionPerformed(evt);
            }
        });
        add(normalAtkBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 120, 30));

        skill2Btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        skill2Btn.setText("Skill 2");
        skill2Btn.setPreferredSize(new java.awt.Dimension(50, 50));
        skill2Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skill2BtnActionPerformed(evt);
            }
        });
        add(skill2Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 120, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("You");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 190, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 190, 10));

        ownMonsterHealthBar.setStringPainted(true);
        add(ownMonsterHealthBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 190, -1));

        ownMonsterName.setForeground(new java.awt.Color(255, 255, 255));
        ownMonsterName.setText("MonsterName");
        add(ownMonsterName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 130, -1));

        enemyTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enemyTitle.setForeground(new java.awt.Color(255, 255, 255));
        enemyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enemyTitle.setText("Enemy (x/3)");
        add(enemyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 190, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 190, 10));

        enemyMonsterHealthBar.setStringPainted(true);
        add(enemyMonsterHealthBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 190, -1));

        enemyMonsterName.setForeground(new java.awt.Color(255, 255, 255));
        enemyMonsterName.setText("EnemyName");
        add(enemyMonsterName, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 130, -1));

        enemyMonsterIcon.setMaximumSize(new java.awt.Dimension(200, 200));
        enemyMonsterIcon.setMinimumSize(new java.awt.Dimension(200, 200));
        enemyMonsterIcon.setPreferredSize(new java.awt.Dimension(200, 200));
        add(enemyMonsterIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));

        ownMonsterIcon.setMaximumSize(new java.awt.Dimension(200, 200));
        ownMonsterIcon.setMinimumSize(new java.awt.Dimension(200, 200));
        ownMonsterIcon.setPreferredSize(new java.awt.Dimension(200, 200));
        add(ownMonsterIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/4@300px.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 430));
    }// </editor-fold>//GEN-END:initComponents

    private void forfeitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forfeitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_forfeitBtnActionPerformed

    private void normalAtkBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalAtkBtnActionPerformed
        battleLogArea.append(ownName + " Used Normal Attack!\n");
        int currAtk = ownAtk - enemyDef;
        currAtk = (currAtk - 5) - rng.nextInt(20);
        if (currAtk <= 0) {
            currAtk = rng.nextInt(5);
        }
        if (rng.nextInt() > 80) {
            currAtk = currAtk * 2;
            battleLogArea.append("It is Critical Attack!\n");
        }
        enemyHP = enemyHP - currAtk;
        enemyMonsterHealthBar.setValue(enemyHP);
        battleLogArea.append(enemyName + " Took " + currAtk + " Damages!\n");
        checkSkillCD();
        checkEnemyHealth();
    }//GEN-LAST:event_normalAtkBtnActionPerformed

    private void skill1BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skill1BtnActionPerformed
        int ownSkillLvl = Integer.parseInt(partyData[currentParty][13]);
        int ownSkillBaseValue = Integer.parseInt(partyData[currentParty][12]);
        int ownSkillValue = ownSkillBaseValue + (ownSkillBaseValue * (ownSkillLvl / 2));
        String skillName = partyData[currentParty][10];
        String skillEffect = partyData[currentParty][11];
        if (skill1CD == 0) {
            switch (skillEffect) {
                case "atk":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    int currAtk = ownAtk - enemyDef;
                    currAtk = (currAtk + ownSkillValue) - rng.nextInt(20);
                    if (currAtk <= 0) {
                        currAtk = rng.nextInt(5);
                    }
                    if (rng.nextInt() > 80) {
                        currAtk = currAtk * 2;
                        battleLogArea.append("It is Critical Attack!\n");
                    }
                    enemyHP = enemyHP - currAtk;
                    enemyMonsterHealthBar.setValue(enemyHP);
                    battleLogArea.append(enemyName + " Took " + currAtk + " Damages!\n");
                    skill1CD = 4;
                    break;

                case "def":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(ownName + " Defense Increased By " + ownSkillValue + " For 3 Turn!\n");
                    ownDefBuffVal = ownSkillValue;
                    ownDef += ownDefBuffVal;
                    ownDefBuffCD = 3;
                    skill1CD = 5;
                    break;

                case "heal":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    ownHP += ownSkillValue;
                    if (ownHP > 100) {
                        ownHP = 100;
                    }
                    battleLogArea.append(ownName + " Healed By " + ownSkillValue + "!\n");
                    skill1CD = 5;
                    break;

                case "inv":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(enemyName + " Can't Attack!");
                    skill1CD = 4;
                    skill1Btn.setEnabled(false);
                    checkSkillCD();
                    return;
            }
            skill1Btn.setEnabled(false);
            checkSkillCD();
            checkEnemyHealth();
        }
    }//GEN-LAST:event_skill1BtnActionPerformed

    private void skill2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skill2BtnActionPerformed
        int ownSkillLvl = Integer.parseInt(partyData[currentParty][18]);
        int ownSkillBaseValue = Integer.parseInt(partyData[currentParty][17]);
        int ownSkillValue = ownSkillBaseValue + (ownSkillBaseValue * (ownSkillLvl / 2));
        String skillName = partyData[currentParty][15];
        String skillEffect = partyData[currentParty][16];
        if (skill2CD == 0) {
            switch (skillEffect) {
                case "atk":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    int currAtk = ownAtk - enemyDef;
                    currAtk = (currAtk + ownSkillValue) - rng.nextInt(20);
                    if (currAtk <= 0) {
                        currAtk = rng.nextInt(5);
                    }
                    if (rng.nextInt() > 80) {
                        currAtk = currAtk * 2;
                        battleLogArea.append("It is Critical Attack!\n");
                    }
                    enemyHP = enemyHP - currAtk;
                    enemyMonsterHealthBar.setValue(enemyHP);
                    battleLogArea.append(enemyName + " Took " + currAtk + " Damages!\n");
                    skill2CD = 4;
                    break;

                case "def":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(ownName + " Defense Increased By " + ownSkillValue + " For 3 Turn!\n");
                    ownDefBuffVal = ownSkillValue;
                    ownDef += ownDefBuffVal;
                    ownDefBuffCD = 3;
                    skill2CD = 5;
                    break;

                case "heal":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    ownHP += ownSkillValue;
                    if (ownHP > 100) {
                        ownHP = 100;
                    }
                    battleLogArea.append(ownName + " Healed By " + ownSkillValue + "!\n");
                    skill2CD = 5;
                    break;

                case "inv":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(enemyName + " Can't Attack!");
                    skill2CD = 4;
                    skill2Btn.setEnabled(false);
                    checkSkillCD();
                    return;
            }
            skill2Btn.setEnabled(false);
            checkSkillCD();
            checkEnemyHealth();
        }
    }//GEN-LAST:event_skill2BtnActionPerformed

    private void skill3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skill3BtnActionPerformed
        int ownSkillLvl = Integer.parseInt(partyData[currentParty][23]);
        int ownSkillBaseValue = Integer.parseInt(partyData[currentParty][22]);
        int ownSkillValue = ownSkillBaseValue + (ownSkillBaseValue * (ownSkillLvl / 2));
        String skillName = partyData[currentParty][20];
        String skillEffect = partyData[currentParty][21];
        if (skill3CD == 0) {
            switch (skillEffect) {
                case "atk":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    int currAtk = ownAtk - enemyDef;
                    currAtk = (currAtk + ownSkillValue) - rng.nextInt(20);
                    if (currAtk <= 0) {
                        currAtk = rng.nextInt(5);
                    }
                    if (rng.nextInt() > 80) {
                        currAtk = currAtk * 2;
                        battleLogArea.append("It is Critical Attack!\n");
                    }
                    enemyHP = enemyHP - currAtk;
                    enemyMonsterHealthBar.setValue(enemyHP);
                    battleLogArea.append(enemyName + " Took " + currAtk + " Damages!\n");
                    skill3CD = 4;
                    break;

                case "def":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(ownName + " Defense Increased By " + ownSkillValue + " For 3 Turn!\n");
                    ownDefBuffVal = ownSkillValue;
                    ownDef += ownDefBuffVal;
                    ownDefBuffCD = 3;
                    skill3CD = 5;
                    break;

                case "heal":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    ownHP += ownSkillValue;
                    if (ownHP > 100) {
                        ownHP = 100;
                    }
                    battleLogArea.append(ownName + " Healed By " + ownSkillValue + "!\n");
                    skill3CD = 5;
                    break;

                case "inv":
                    battleLogArea.append(ownName + " Used " + skillName + "!\n");
                    battleLogArea.append(enemyName + " Can't Attack!");
                    skill3CD = 4;
                    skill3Btn.setEnabled(false);
                    checkSkillCD();
                    return;
            }
            skill3Btn.setEnabled(false);
            checkSkillCD();
            checkEnemyHealth();
        }
    }//GEN-LAST:event_skill3BtnActionPerformed

    private void openItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemBtnActionPerformed
        JOptionPane.showMessageDialog(this, "Disabled!");
//        Battle_Item_Select bis = new Battle_Item_Select(this, mf.getPlayer().getUserID());
//        bis.setVisible(true);
    }//GEN-LAST:event_openItemBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea battleLogArea;
    private javax.swing.JProgressBar enemyMonsterHealthBar;
    private javax.swing.JLabel enemyMonsterIcon;
    private javax.swing.JLabel enemyMonsterName;
    private javax.swing.JLabel enemyTitle;
    private javax.swing.JButton forfeitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton normalAtkBtn;
    private javax.swing.JButton openItemBtn;
    private javax.swing.JProgressBar ownMonsterHealthBar;
    private javax.swing.JLabel ownMonsterIcon;
    private javax.swing.JLabel ownMonsterName;
    private javax.swing.JButton skill1Btn;
    private javax.swing.JButton skill2Btn;
    private javax.swing.JButton skill3Btn;
    // End of variables declaration//GEN-END:variables
}
