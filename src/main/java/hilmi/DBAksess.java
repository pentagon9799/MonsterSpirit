/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilmi;

import com.ish.monsterspirit.*;
import java.sql.*;
import javax.swing.JTable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String user = "root", pass = "", urlcon = "jdbc:mysql://localhost:3306/", sql = "", DB = "db_monsterspirit";

    public ResultSet searchData(String tableName, String columnName, String searchValue) {
        ResultSet rsReturn = null;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnName + " = " + searchValue);
            rsReturn = statementBaru.executeQuery();
            conGetData.close();
//            setPs(getCon().prepareStatement(getSql()));
//            setRs(getPs().executeQuery());
//            getCon().close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
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

    public DefaultTableModel getOpponentDataTable(String currentUserID) throws SQLException {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM party "
                    + "INNER JOIN player ON party.User_id = player.User_id "
                    + "WHERE Public_slot != 0 "
                    + "AND party.User_id != " + currentUserID
                    + " GROUP BY party.User_id");
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

    public boolean checkOnlineOpponent(String uid) {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT COUNT(*) AS list FROM list_party "
                    + "WHERE Public_slot != 0 "
                    + "AND User_id != " + uid
                    + " ORDER BY Public_slot ASC ");
            rsReturn = statementBaru.executeQuery();

            while (rsReturn.next()) {
                String jumlah = rsReturn.getString("list");
                System.out.println("Jumlah: "+jumlah);
                if (Integer.parseInt(jumlah) > 0) {
                    return true;
                } else {
                    return false;
                }
            }

            conGetData.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    public boolean checkPartyAvailable(String uid) {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT COUNT(*) AS list FROM list_party "
                    + "WHERE User_id = " + uid);
            rsReturn = statementBaru.executeQuery();

            while (rsReturn.next()) {
                String jumlah = rsReturn.getString("list");
                System.out.println("Jumlah: "+jumlah);
                if (Integer.parseInt(jumlah) > 2) {
                    return true;
                } else {
                    return false;
                }
            }

            conGetData.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    public boolean checkPartyAvailable(String uid, int slot) {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT COUNT(*) AS list FROM list_party "
                    + "WHERE User_id = " + uid+" AND public_slot="+slot);
            rsReturn = statementBaru.executeQuery();

            while (rsReturn.next()) {
                String jumlah = rsReturn.getString("list");
                System.out.println("Jumlah: "+jumlah);
                if (Integer.parseInt(jumlah) > 0) {
                    return true;
                } else {
                    return false;
                }
            }

            conGetData.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public String[][] getOpponentPublicLineup(String opponentUserID) throws SQLException {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT * FROM list_party "
                    + "WHERE Public_slot != 0 "
                    + "AND User_id = " + opponentUserID + " "
                    + "ORDER BY Public_slot ASC "
                    + "LIMIT 3");
            rsReturn = statementBaru.executeQuery();

            ResultSetMetaData metaData = rsReturn.getMetaData();
//            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();

            ArrayList<ArrayList<String>> opponentDataList = new ArrayList<ArrayList<String>>();
            while (rsReturn.next()) {
                ArrayList<String> innerData = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    innerData.add(rsReturn.getObject(i).toString());
                }
                opponentDataList.add(innerData);
            }

            String[][] dataToReturn = new String[opponentDataList.size()][columnCount];
            int i = 0;
            for (ArrayList<String> data : opponentDataList) {
                int j = 0;
                for (String data2 : data) {
                    dataToReturn[i][j] = data2;
                    j++;
                }
                i++;
            }

            conGetData.close();

            return dataToReturn;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return null;
    }

    public boolean reduceItem(String userID, String itemID, int currentQTY) {
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru;

            if (currentQTY <= 1) {
                statementBaru = conGetData.prepareStatement("DELETE FROM inventory WHERE Item_id = " + itemID + " AND User_id = " + userID);
            } else {
                statementBaru = conGetData.prepareStatement("UPDATE inventory SET qty = " + (currentQTY - 1) + " WHERE Item_id = " + itemID + " AND User_id = " + userID);
            }

            if (statementBaru.executeUpdate() == 0) {
                System.err.println("Data Not Found!");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        return false;
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

    public void setUrlcon(String urlcon) {
        this.urlcon = urlcon;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection readOnly(String DB) throws SQLException {
        try {
            setCon(DriverManager.getConnection(this.urlcon + DB, user, pass)); //return this.con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.con;
    }

    public Connection readOnly(String url, String user, String pass, String DB) throws SQLException {
        try {
            setCon(DriverManager.getConnection(url + DB, user, pass)); //return this.con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.con;
    }

    public void readandShow(String url, String user, String pass, String DB) throws SQLException {
        try {
            setCon(DriverManager.getConnection(url + DB, user, pass));
            setPs(getCon().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readandShow() throws SQLException {
        try {
            setCon(DriverManager.getConnection(this.urlcon + this.DB, user, pass));
            setPs(getCon().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeToArray(String url, String user, String pass, String DB) throws SQLException {
        try {
            setCon(DriverManager.getConnection(url + DB, user, pass));
            setPs(getCon().prepareStatement(getSql()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void write() throws SQLException {
        try {
            setCon(DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass));
            setPs(getCon().prepareStatement(getSql()));
            getPs().executeUpdate();
            getCon().close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int rowCount(String sqlstatement)
    {
        int row=0;
        setSql(sqlstatement);
        try{
            readandShow();
            while(getRs().next())
            {
                row++;
            }
        getCon().close();
        }catch(SQLException ex)
        {
            Logger.getLogger(DBAksess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

}
