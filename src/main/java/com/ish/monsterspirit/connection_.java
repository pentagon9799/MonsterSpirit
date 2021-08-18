
package com.ish.monsterspirit;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import hilmi.DBAksess;
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
            PreparedStatement ps = con.prepareStatement(sqlstatement);
            rs = ps.executeQuery();
        }catch(SQLException ex){
            System.out.println("Query error");
        }
        return rs;
    }
    
    public void save(String path, String name,String sqlstatement){
        dba.setCon(connect());//Connection con = connect();
        //String insert = "insert into usuario(nombre,foto) values(?,?)";
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(path);
            fi = new FileInputStream(file);
            
            dba.setPs(dba.getCon().prepareStatement(sqlstatement));//ps = con.prepareStatement(insert);
            dba.getPs().setString(1, name);//ps.setString(1, nombre);
            dba.getPs().setInt(2, 100);
            dba.getPs().setInt(3, 100);
            dba.getPs().setBinaryStream(4, fi);//ps.setBinaryStream(2, fi);
            
            dba.getPs().executeUpdate();//ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error adding user "+ex.getMessage());
        }

//        dba.setSql(sqlstatement);
//        try{
//        dba.write();
//        }
//        catch(SQLException ex)
//        {
//            System.out.println("Query error");
//        }
    }
    
}
