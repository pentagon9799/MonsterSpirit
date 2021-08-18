
package ricylebin;

import septian.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ish.monsterspirit.DBAksess;
import com.ish.monsterspirit.DBUtils;
import java.sql.SQLException;

public class connection_ {
    DBAksess dba = new DBAksess();
    
    public Connection connect(){
       dba.setCon(null);
        try
        {
            dba.setCon(DriverManager.getConnection(dba.getUrlcon()+dba.getDB(),dba.getUser(),dba.getPass()));
            System.out.println("Online");
        }
        catch(SQLException ex)
        {
            System.out.println("Error: "+ex.getMessage());
        }
        return dba.getCon();
    
    }
    
    public ResultSet display(String sqlstatement){
        Connection con = connect();
        ResultSet rs = null;
        try{
            //PreparedStatement ps = con.prepareStatement("select * from usuario");
            PreparedStatement ps = con.prepareStatement(sqlstatement);//("select * from gambar");
            rs = ps.executeQuery();
        }catch(SQLException ex){
            System.out.println("Query error");
        }
        return rs;
    }
    
    public void save(/*String path, String name,*/String sqlstatement){
        dba.setSql(sqlstatement);
        try{
        dba.write();
        }
        catch(SQLException ex)
        {
            System.out.println("Query error");
        }
    }
    
}
