/*
 * Hilmi Abdurrahman Fakhrudin - 1807422008
 */
package hilmi;

import javax.swing.ImageIcon;

/**
 *
 * @author hilmi
 */
public class Battle_Campaign_LvlSelect_Panel extends javax.swing.JPanel {
    
    BattleMainFrame mf;
    
    /**
     * Creates new form Battle_Campaign_LvlSelect_Panel
     */
    public Battle_Campaign_LvlSelect_Panel(BattleMainFrame mf) {
        initComponents();
        this.mf = mf;
        campaign_stg1_lvl1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("uiIcon/lvl1btn.png")));
        campaign_stg1_lvl2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("uiIcon/lvl2btn.png")));
        campaign_stg1_lvl3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("uiIcon/lvl3btn.png")));
        campaign_stg1_lvlBoss.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("uiIcon/lvl4btn.png")));
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
        campaign_stg1_lvl1 = new javax.swing.JButton();
        campaign_stg1_lvl2 = new javax.swing.JButton();
        campaign_stg1_lvl3 = new javax.swing.JButton();
        campaign_stg1_lvlBoss = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(848, 480));
        setMinimumSize(new java.awt.Dimension(848, 480));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 40));

        campaign_stg1_lvl1.setMaximumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl1.setMinimumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl1.setPreferredSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campaign_stg1_lvl1ActionPerformed(evt);
            }
        });
        add(campaign_stg1_lvl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 90, 80));

        campaign_stg1_lvl2.setToolTipText("");
        campaign_stg1_lvl2.setMaximumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl2.setMinimumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl2.setPreferredSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campaign_stg1_lvl2ActionPerformed(evt);
            }
        });
        add(campaign_stg1_lvl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 90, 80));

        campaign_stg1_lvl3.setMaximumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl3.setMinimumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl3.setPreferredSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvl3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campaign_stg1_lvl3ActionPerformed(evt);
            }
        });
        add(campaign_stg1_lvl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 90, 80));

        campaign_stg1_lvlBoss.setMaximumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvlBoss.setMinimumSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvlBoss.setPreferredSize(new java.awt.Dimension(75, 75));
        campaign_stg1_lvlBoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campaign_stg1_lvlBossActionPerformed(evt);
            }
        });
        add(campaign_stg1_lvlBoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 90, 80));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Campaign Level");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/3@300px.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 850, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void campaign_stg1_lvl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campaign_stg1_lvl1ActionPerformed
        mf.runBattleCampaignPartySelect(1);
        this.setVisible(false);
    }//GEN-LAST:event_campaign_stg1_lvl1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        mf.runBattleMenu();
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void campaign_stg1_lvl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campaign_stg1_lvl2ActionPerformed
        mf.runBattleCampaignPartySelect(2);
        this.setVisible(false);
    }//GEN-LAST:event_campaign_stg1_lvl2ActionPerformed

    private void campaign_stg1_lvl3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campaign_stg1_lvl3ActionPerformed
        mf.runBattleCampaignPartySelect(3);
        this.setVisible(false);
    }//GEN-LAST:event_campaign_stg1_lvl3ActionPerformed

    private void campaign_stg1_lvlBossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campaign_stg1_lvlBossActionPerformed
        mf.runBattleCampaignPartySelect(4);
        this.setVisible(false);
    }//GEN-LAST:event_campaign_stg1_lvlBossActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton campaign_stg1_lvl1;
    private javax.swing.JButton campaign_stg1_lvl2;
    private javax.swing.JButton campaign_stg1_lvl3;
    private javax.swing.JButton campaign_stg1_lvlBoss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
