/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricylebin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author delimiter_
 */
public class dashboard extends javax.swing.JPanel {

    /**
     * Creates new form dashboard
     */

    public dashboard() {
        initComponents();
        

    }
    
    public void setusername(String s) {
       this.username.setText(s);
    }
  public void setmoney(String i) {
       this.money.setText(i);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JLabel();
        money = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setText("jLabel1");
        add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 20, -1, -1));

        money.setText("jLabel2");
        add(money, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 52, -1, -1));

        jLabel1.setText("Username : ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 20, -1, -1));

        jLabel3.setText("Money :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 52, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel money;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
