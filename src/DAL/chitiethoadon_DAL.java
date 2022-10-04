/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hong Xuan
 */
public class chitiethoadon_DAL {

    Connection con = null;
    String dburl = "";

    public boolean createconnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dburl = "jdbc:sqlserver://localhost:1433;"
                    + "DatabaseName=QUANLIPHONGKHAM";
            con = DriverManager.getConnection(dburl, "sa", "sa");
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
            return false;

        }
    }
    
    public void closeconnection() {
        try{
            if (con != null) {
            con.close();
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
    }
    //truy xuất chi tiết hoa đơn theo mã hóa đơn
    public ArrayList<chitiethoadon_DTO> theoma(String mahd)
    {
        ArrayList<chitiethoadon_DTO> arr=new ArrayList<chitiethoadon_DTO>();
        if(this.createconnection())
        {
            try{
                String sql="SELECT * FROM CHITIETHOADON \n" +
                            "where MAHD='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    chitiethoadon_DTO cthd=new chitiethoadon_DTO();
                    cthd.setMahd(rs.getString("MAHD"));
                    cthd.setMathuoc(rs.getString("MATHUOC"));
                    cthd.setSl(rs.getInt("SL"));
                    cthd.setTt(rs.getFloat("THANHTIEN"));
                    arr.add(cthd);
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally{
                this.closeconnection();
            }
        }
        return arr;
    }
    public String gettenthuoc(String mathuoc) {
        String tenthuoc = "";
        if (this.createconnection()) {
            try {
                String sql = "select top(1) t.TENTHUOC\n"
                        + "from thuoc t,CHITIETHOADON cthd\n"
                        + "where t.MATHUOC=cthd.MATHUOC and t.MATHUOC='" + mathuoc + "' ";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    tenthuoc = rs.getString("TENTHUOC");

                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                this.closeconnection();
            }
        }
        return tenthuoc;
    }
   
    public double getgia(String mathuoc)
    {
        double gia=0;
         if (this.createconnection()) {
            try {
                String sql = "SELECT top(1) t.GIA \n" +
                "FROM CHITIETHOADON ct , thuoc t\n" +
                "where t.MATHUOC='"+mathuoc+"'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    gia = rs.getDouble("GIA");

                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                this.closeconnection();
            }
        }

        return gia;
    }
    public boolean hascthd(String mahd,String mathuoc)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            try{
                String sql="select *\n" +
                            "from CHITIETHOADON\n" +
                            "where MAHD='"+mahd+"' and MATHUOC='"+mathuoc+"'";
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
            finally{
                this.closeconnection();
            }
        }
        return kq;
    }
    public boolean them(chitiethoadon_DTO cthd)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            String mahd=cthd.getMahd();
            String mathuoc=cthd.getMathuoc();
            int sl=cthd.getSl();
            double thanhtien=cthd.getTt();
            try{
                String sql="insert into CHITIETHOADON (MAHD,mathuoc,SL,THANHTIEN) "
                        + "values('"+mahd+"','"+mathuoc+"',"+sl+","+thanhtien+")";
                Statement stm=con.createStatement();
                int ins=stm.executeUpdate(sql);
                if(ins>0)
                {
                    kq=true;
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally{
                
            }
        }
        return kq;
    }
    public ArrayList<chitiethoadon_DTO> thongketheohd(String ngaybatdau,String ngayketthuc)
    {
        ArrayList<chitiethoadon_DTO> arr=new ArrayList<chitiethoadon_DTO>();
        if(this.createconnection())
        {
            try
            {
                String sql="select cthd.*\n" +
                "from HOADON hd , CHITIETHOADON cthd\n" +
                "where (hd.MAHD=cthd.MAHD) and (hd.NGAY between '"+ngaybatdau+"' and '"+ngayketthuc+"')";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    chitiethoadon_DTO cthd_DTO=new chitiethoadon_DTO();
                    cthd_DTO.setMahd(rs.getString("mahd"));
                    cthd_DTO.setMathuoc(rs.getString("mathuoc"));
                    cthd_DTO.setSl(rs.getInt("SL"));
                    cthd_DTO.setTt(rs.getDouble("Thanhtien"));
                    arr.add(cthd_DTO);
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally
            {
                this.closeconnection();
            }
        }
        return arr;
    }
    public String getngay(String mahd) {
        String ngay = "";
        if (this.createconnection()) {
            try {
                String sql = "select top(1) hd.NGAY\n" +
                                "from HOADON hd , CHITIETHOADON cthd\n" +
                                "where (hd.MAHD=cthd.MAHD) and hd.MAHD='"+mahd+"' ";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    ngay = rs.getString("Ngay");

                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                this.closeconnection();
            }
        }
        return ngay;
    }
    public static void main(String[] args) {
        chitiethoadon_DAL cthd = new chitiethoadon_DAL();
        System.out.println(cthd.getngay("HD_0001"));
    }
    
}
