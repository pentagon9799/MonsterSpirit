
package com.ish.monsterspirit;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import hilmi.DBAksess;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import septian.upgradePartyPanel;
//import com.ish.monsterspirit.DBUtils;
public class ReadTablein {
private upgradePartyPanel sp;
private Object[][] selectedParty;

    public Object[][] getSelectedParty() {
        return selectedParty;
    }
    
public void display_table(JTable tabla, String sqlstetment){
    int i=0;
    selectedParty = new Object[rowCounter(sqlstetment)][25];
    connection_ conn = new connection_();
    ResultSet rs = conn.display(sqlstetment);
    tabla.setDefaultRenderer(Object.class, new TableImage());
    DefaultTableModel dt = new DefaultTableModel();
    dt.addColumn("Name");
    dt.addColumn("Image");
     dt.addColumn("qty");
    
    try{
        while(rs.next()){
                Object[] row = new Object[3];
                row[0] = rs.getObject(2);
                row[2] = rs.getObject(7);
                selectedParty[i][0]=rs.getObject(1);
                selectedParty[i][1]=rs.getObject(2);
                selectedParty[i][2]=rs.getObject(3);
                selectedParty[i][3]=rs.getObject(4);
                selectedParty[i][4]=rs.getObject(5);
                selectedParty[i][5]=rs.getObject(6);
                selectedParty[i][6]=rs.getObject(7);
                selectedParty[i][7]=rs.getObject(8);
                selectedParty[i][8]=rs.getObject(9);

                System.out.println(
                        selectedParty[i][0]+"|"+
                        selectedParty[i][1]+"|"+
                        selectedParty[i][2]+"|"+
                        selectedParty[i][3]+"|"+
                        selectedParty[i][4]+"|"+
                        selectedParty[i][5]+"|"+
                        selectedParty[i][6]+"|"+
                        selectedParty[i][7]+"|"+
                        selectedParty[i][8]+"|"+
                        selectedParty[i][9]+"|");

                Blob blob = rs.getBlob(9);
                if(blob != null){
                   try{
                        byte[] data = blob.getBytes(1, (int)blob.length());
                        //byte[] data = blob.g//getBytes(1, (int)blob.length());
                        BufferedImage img = null;
                        try{
                            img = ImageIO.read(new ByteArrayInputStream(data));
                            System.out.println("ini data :"+data);
                        }catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    ImageIcon icono = new ImageIcon(img);
                    row[1] = new JLabel(icono);
                        }catch(Exception ex){
                            row[1] = "No Imagen";
                        }
                }
                else{
                    row[1] = "No Imagen";
                }
                dt.addRow(row);
                i++;
            }  
        tabla.setModel(dt);
        tabla.setRowHeight(64);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        ex.printStackTrace();   
    }  
}
public int rowCounter(String sqlstatement)
{DBAksess dbr = new DBAksess();
    dbr.setSql(sqlstatement);
    int row=0;
    try{
        dbr.readandShow();
        while(dbr.getRs().next())
        {
            row++;
        }
        dbr.getCon().close();
    }catch(SQLException ex)
    {
        Logger.getLogger(ReadTablein.class.getName()).log(Level.SEVERE, null, ex);
    }
    return row;
}
}
