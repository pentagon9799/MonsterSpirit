
package ricylebin;

import com.ish.monsterspirit.connection_;
import com.ish.monsterspirit.TableImage;
import septian.*;
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
import com.ish.monsterspirit.DBAksess;
import com.ish.monsterspirit.DBUtils;

public class ReadTable {
    
public void display_table(JTable tabla, String sqlstetment){
    connection_ conn = new connection_();
    Connection con = conn.connect();
    ResultSet rs = conn.display(sqlstetment);
    //Image img = null;   
    tabla.setDefaultRenderer(Object.class, new TableImage());
    DefaultTableModel dt = new DefaultTableModel();
    //dt.addColumn("Name");
    //dt.addColumn("Foto");
    dt.addColumn("Name");
    dt.addColumn("Image");
    
    try{
        
        while(rs.next()){
                Object[] row = new Object[2];
                row[0] = rs.getObject(2);
                
                Blob blob = rs.getBlob(5);

                if(blob != null){
                   try{
                        byte[] data = blob.getBytes(1, (int)blob.length());
                        BufferedImage img = null;
                        try{
                            img = ImageIO.read(new ByteArrayInputStream(data));
                            System.out.println(data);
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
            }
        
        tabla.setModel(dt);
        tabla.setRowHeight(64);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        ex.printStackTrace();   
    }  
}
    
}
