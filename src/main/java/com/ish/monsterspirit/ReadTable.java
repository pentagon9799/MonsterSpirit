
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
public class ReadTable {
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
    try{
        while(rs.next()){
                Object[] row = new Object[2];
                row[0] = rs.getObject(3);
                selectedParty[i][0]=rs.getObject(1);
                selectedParty[i][1]=rs.getObject(2);
                selectedParty[i][2]=rs.getObject(3);
                selectedParty[i][3]=rs.getObject(4);
                selectedParty[i][4]=rs.getObject(5);
                selectedParty[i][5]=rs.getObject(6);
                selectedParty[i][6]=rs.getObject(7);
                selectedParty[i][7]=rs.getObject(8);
                selectedParty[i][8]=rs.getObject(9);
                selectedParty[i][9]=rs.getObject(10);
                selectedParty[i][10]=rs.getObject(11);
                selectedParty[i][11]=rs.getObject(12);
                selectedParty[i][12]=rs.getObject(13);
                selectedParty[i][13]=rs.getObject(14);
                selectedParty[i][14]=rs.getObject(15);
                selectedParty[i][15]=rs.getObject(16);
                selectedParty[i][16]=rs.getObject(17);
                selectedParty[i][17]=rs.getObject(18);
                selectedParty[i][18]=rs.getObject(19);
                selectedParty[i][19]=rs.getObject(20);
                selectedParty[i][20]=rs.getObject(21);
                selectedParty[i][21]=rs.getObject(22);
                selectedParty[i][22]=rs.getObject(23);
                selectedParty[i][23]=rs.getObject(24);
                selectedParty[i][24]=rs.getObject(25);
                
                System.out.println(selectedParty[i][0]+
                        "|"+selectedParty[i][1]+"|"+
                        selectedParty[i][2]+"|"+
                        selectedParty[i][3]+"|"+
                        selectedParty[i][4]+"|"+
                        selectedParty[i][5]+"|"+
                        selectedParty[i][6]+"|"+
                        selectedParty[i][7]+"|"+
                        selectedParty[i][8]+"|"+
                        selectedParty[i][9]+"|"+
                        selectedParty[i][10]+"|"+
                        selectedParty[i][11]+"|"+
                        selectedParty[i][12]+"|"+
                        selectedParty[i][13]+"|"+
                        selectedParty[i][14]+"|"+
                        selectedParty[i][15]+"|"+
                        selectedParty[i][16]+"|"+
                        selectedParty[i][17]+"|"+
                        selectedParty[i][18]+"|"+
                        selectedParty[i][19]+"|"+
                        selectedParty[i][20]+"|"+
                        selectedParty[i][21]+"|"+
                        selectedParty[i][22]+"|"+
                        selectedParty[i][23]+"|"+
                        selectedParty[i][24]+"|");
                
                Blob blob = rs.getBlob(8);
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
        Logger.getLogger(ReadTable.class.getName()).log(Level.SEVERE, null, ex);
    }
    return row;
}
public Object[][] display_table2(JTable tabla, String sqlstetment){
    int i=0;
    Object[][] item = new Object[rowCounter(sqlstetment)][7];
    //selectedParty = new Object[rowCounter(sqlstetment)][25];
    connection_ conn = new connection_();
    ResultSet rs = conn.display(sqlstetment);
    tabla.setDefaultRenderer(Object.class, new TableImage());
    DefaultTableModel dt = new DefaultTableModel();
    dt.addColumn("Name");
    dt.addColumn("Image");
    try{
        while(rs.next()){
                Object[] row = new Object[2];
                row[0] = rs.getObject(2);
                item[i][0]=rs.getObject(1);
                item[i][1]=rs.getObject(2);
                item[i][2]=rs.getObject(3);
                item[i][3]=rs.getObject(4);
                item[i][4]=rs.getObject(5);
                item[i][5]=rs.getObject(6);
                item[i][6]=rs.getObject(7);
                Blob blob = rs.getBlob(7);
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
    return item;
}
}
