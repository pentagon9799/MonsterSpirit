/*
 * Hilmi Abdurrahman Fakhrudin - 1807422008
 */
package hilmi;

import java.sql.*;

/**
 *
 * @author hilmi
 */
public class Player extends Game {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String user = "root", pass = "", urlcon = "jdbc:mysql://localhost:3306/", sql = "", DB = "db_monsterspirit";

    int money;

    public int getMoney() {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT money FROM player WHERE User_id = " + getId());
            rsReturn = statementBaru.executeQuery();

            while (rsReturn.next()) {
                return Integer.parseInt(rsReturn.getString("money"));
            }

            conGetData.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return -1;
    }

    public boolean addMoney(int value) {
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("UPDATE player SET money = (money + " + value + ") WHERE User_id = " + getId());

            if (statementBaru.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    public boolean spendMoney(int value) {
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("UPDATE player SET money = (money - " + value + ") WHERE User_id = " + getId());

            if (statementBaru.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    public boolean reduceItem(String itemID, int currentQTY) {
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru;

            if (currentQTY <= 1) {
                statementBaru = conGetData.prepareStatement("DELETE FROM inventory WHERE Item_id = " + itemID + " AND User_id = " + getId());
            } else {
                statementBaru = conGetData.prepareStatement("UPDATE inventory SET qty = " + (currentQTY - 1) + " WHERE Item_id = " + itemID + " AND User_id = " + getId());
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
    
     public boolean checkItemAvailable(String uid) {
        ResultSet rsReturn;
        try {
            Connection conGetData = DriverManager.getConnection(this.urlcon + this.DB, this.user, this.pass);
            PreparedStatement statementBaru = conGetData.prepareStatement("SELECT COUNT(*) AS list FROM inventory "
                    + "WHERE User_id = " + uid);
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

}
