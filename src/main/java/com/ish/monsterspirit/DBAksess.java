/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ish.monsterspirit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JTable;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class DBAksess {
    //asdasda
    private Connection con;    
    private PreparedStatement ps;
    private ResultSet rs;
    private String user="root",pass="",urlcon="jdbc:mysql://localhost:3306/",sql="",DB="db_monsterspirit";    

    
    public ResultSet getData(String table){
        ResultSet rsReturn = null;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon+this.DB,this.user,this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM "+table);
            rsReturn = statementBaru.executeQuery();
            conGetData.close();
//            setPs(getCon().prepareStatement(getSql()));
//            setRs(getPs().executeQuery());
//            getCon().close();
        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }
        return rsReturn;
    }
    
    public DefaultTableModel getTableData(String tableName, String columnName, String searchValue) throws SQLException {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnName + " = " + searchValue);
            rsReturn = statementBaru.executeQuery();

            ResultSetMetaData metaData = rsReturn.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rsReturn.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rsReturn.getObject(columnIndex));
                }
                data.add(vector);
            }

            conGetData.close();

            return new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }
    
    public DefaultTableModel getItemData() throws SQLException {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM item");
            rsReturn = statementBaru.executeQuery();

            ResultSetMetaData metaData = rsReturn.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rsReturn.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rsReturn.getObject(columnIndex));
                }
                data.add(vector);
            }

            conGetData.close();

            return new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }
    
    public String getSql() {
        return sql;
    }

    public String getDB() {
        return DB;
    }

    public void setDB(String DB) {
        this.DB = DB;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getUrlcon() {
        return urlcon;
    }

    public  void setUrlcon(String urlcon) {
        this.urlcon = urlcon;
    }

    public  Connection getCon() {
        return con;
    }    

    public  void setCon(Connection con) {
        this.con = con;
    }
    public Connection readOnly(String DB) throws SQLException
    {
        try
        {
            setCon(DriverManager.getConnection(this.urlcon+DB,user,pass)); //return this.con;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                
        return this.con;
    }
    public Connection readOnly(String url, String user, String pass,String DB) throws SQLException
    {
        try
        {
            setCon(DriverManager.getConnection(url+DB,user,pass)); //return this.con;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                
        return this.con;
    }
    public void readandShow(String url, String user, String pass,String DB) throws SQLException
    {
        try
        {
            setCon(DriverManager.getConnection(url+DB,user,pass));
            setPs(getCon().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                
    }
    public void readandShow() throws SQLException
    {
        try
        {
            setCon(DriverManager.getConnection(this.urlcon+this.DB,user,pass));
            setPs(getCon().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                
    }
    public void writeToArray(String url, String user, String pass, String DB) throws SQLException
    {        
        try
        {
            setCon(DriverManager.getConnection(url+DB,user,pass));
            setPs(getCon().prepareStatement(getSql()));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void write() throws SQLException
    {        
        try
        {
            setCon(DriverManager.getConnection(this.urlcon+this.DB,this.user,this.pass));
            setPs(getCon().prepareStatement(getSql()));
            getPs().executeUpdate();
            getCon().close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    
}
