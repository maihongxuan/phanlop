/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.*;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Hong Xuan
 */
public class hoadon_DAL {
     Connection con=null;
    String dburl="";
     public boolean createConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dburl = "jdbc:sqlserver://localhost:1433;"
                    + "DatabaseName=QUANLIPHONGKHAM";
            con = DriverManager.getConnection(dburl, "sa", "sa");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public ArrayList<hoadon_DTO> getall()
    {
        ArrayList<hoadon_DTO> arr=new ArrayList<hoadon_DTO>();
        
                if (this.createConnection() == true) {
            ResultSet rs = null;
            try {
                String sql = "select * from hoadon";
                Statement s = con.createStatement();
                rs = s.executeQuery(sql);
                 while (rs.next()) {
                hoadon_DTO hd=new hoadon_DTO();
                hd.setMahd(rs.getString("MAHD"));
                hd.setManv(rs.getString("MANV"));
                hd.setMabn(rs.getString("MABN"));
                hd.setNgay(rs.getString("NGAY"));
                hd.setGio(rs.getString("GIO"));
                
                arr.add(hd);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            finally{
                this.closeConnection();
            }
        }
        return arr;
    }
    //kiểm tra xem mã hóa đơn có tồn tại hay không
    public boolean hasmahd(String mahd)
    {
        boolean kq=false;
        if(this.createConnection())
        {
            try{
                String sql="select *\n" +
                            "from hoadon\n" +
                            "where MAHD='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    kq=true;
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally
            {
                this.closeConnection();
            }
        }
        return kq;
    }
    public boolean them(hoadon_DTO hd)
    {
        boolean rs=false;
        String mahd=hd.getMahd();
        String mabn=hd.getMabn();
        String manv=hd.getManv();
        String ngay=hd.getNgay();
        String gio=hd.getGio();
        if(this.createConnection())
        {
            try{
                String sql="insert into hoadon(mahd,mabn,manv,ngay,gio) "
                        + "values('"+mahd+"','"+mabn+"','"+manv+"','"+ngay+"','"+gio+ "')";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    rs=true;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally{
                this.closeConnection();
            }
        }
        return rs;
    }
    public ArrayList<chitiethoadon_DTO> connectioncthd(String mahd)
    {
        ArrayList<chitiethoadon_DTO> arr=new ArrayList<chitiethoadon_DTO>();
        if(this.createConnection())
        {
            try{
                String sql="select cthd.MAHD,cthd.MATHUOC,cthd.MATHUOC,cthd.SL,cthd.THANHTIEN " +
                "from hoadon hd,CHITIETHOADON cthd " +
                "where hd.MAHD='"+mahd+"' and cthd.MAHD='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    chitiethoadon_DTO cthd_DTO=new chitiethoadon_DTO();
                    cthd_DTO.setMahd(rs.getString("MAHD"));
                    cthd_DTO.setMathuoc(rs.getString("MATHUOC"));
                    cthd_DTO.setSl(rs.getInt("SL"));
                    cthd_DTO.setTt(rs.getDouble("thanhtien"));
                    arr.add(cthd_DTO);
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally{
                this.closeConnection();
            }
        }
        return arr;
    }
    public boolean hashd(String mahd)
    {
        boolean kq=false;
        if(this.createConnection())
        {
            try
            {
                String sql="select *\n" +
                            "from HOADON\n" +
                            "where mahd='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    kq=true;
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally
            {
                this.closeConnection();
            }
        }
        return kq;
    }
    public static void main(String[] args) {
        hoadon_DAL hd=new hoadon_DAL();
//        hoadon_DTO hd_DTO=new hoadon_DTO();
//        hd_DTO.setMahd("HD_0009");
//        hd_DTO.setMabn("BN_0001");
//        hd_DTO.setManv("NV_0006");
//        hd_DTO.setNgay("2002-02-02");
//        hd_DTO.setGio("9:00");
        System.out.println(hd.hasmahd("HD_1"));
    }
}
