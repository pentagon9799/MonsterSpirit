///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package septian;
//import hilmi.DBAksess;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
///**
// *
// * @author User
// */
//public class Player_ {
//    DBAksess dba= new DBAksess();
//    Object[][] party_data;
//    
//    public Player_(String UID)
//    {
//        dba.setSql("select * from list_party2 where user_id="+UID);
//        party_data = new Object[1][rowCount("list_party2")];
//        try
//        {
//            dba.readandShow();
//            while(dba.getRs().next())
//            {
//                party_data[i][0]=rs.getObject(1);
//                party_data[i][1]=rs.getObject(2);
//                party_data[i][2]=rs.getObject(3);
//                party_data[i][3]=rs.getObject(4);
//                party_data[i][4]=rs.getObject(5);
//                party_data[i][5]=rs.getObject(6);
//                party_data[i][6]=rs.getObject(7);
//                party_data[i][7]=rs.getObject(8);
//                party_data[i][8]=rs.getObject(9);
//                party_data[i][9]=rs.getObject(10);
//                party_data[i][10]=rs.getObject(11);
//                party_data[i][11]=rs.getObject(12);
//                party_data[i][12]=rs.getObject(13);
//                party_data[i][13]=rs.getObject(14);
//                party_data[i][14]=rs.getObject(15);
//                party_data[i][15]=rs.getObject(16);
//                party_data[i][16]=rs.getObject(17);
//                party_data[i][17]=rs.getObject(18);
//                party_data[i][18]=rs.getObject(19);
//                party_data[i][19]=rs.getObject(20);
//                party_data[i][20]=rs.getObject(21);
//                party_data[i][21]=rs.getObject(22);
//                party_data[i][22]=rs.getObject(23);
//                party_data[i][23]=rs.getObject(24);
//                party_data[i][24]=rs.getObject(25);
//            }
//        }catch(SQLException ex)
//        {
//            Logger.getLogger(Player_.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public int coloumnCount(String tablename)
//    {
//        int row=0;
//        dba.setSql("select * from "+tablename);
//        try{
//            dba.readandShow();
//            //while(dba.getRs().next())
//            //{
//                row++;
//                dba.getRs().getC
//            //}
//        dba.getCon().close();
//        }catch(SQLException ex)
//        {
//            Logger.getLogger(Player_.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return row;
//    }
//}
