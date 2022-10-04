/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.*;

import DTO.*;
import java.util.ArrayList;
/**
 *
 * @author Hong Xuan
 */
public class taikhoan_DAL {
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
    public ArrayList<taikhoan_DTO> getall()
    {
        ArrayList<taikhoan_DTO> arr=new ArrayList<taikhoan_DTO>();
        if (this.createConnection() == true) {
            ResultSet rs = null;
            try {
                String sql = "select * from taikhoan";
                Statement s = con.createStatement();
                rs = s.executeQuery(sql);
                 while (rs.next()) {
                taikhoan_DTO tk=new taikhoan_DTO();
                tk.setManv(rs.getString("MANV"));
                tk.setPass(rs.getString("PASSWORDS"));
                tk.setTrangthai(rs.getInt("trangthai"));
                arr.add(tk);
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
    public boolean hasnhanvien(String manv)
    {
        boolean rs=false;
        try{
            if(this.createConnection())
            {
                String sql="SELECT * FROM TAIKHOAN WHERE MANV='"+manv+"'";
                Statement stm=con.createStatement();
                ResultSet i=stm.executeQuery(sql);
                rs=i.next();
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean themtaikhoan(taikhoan_DTO tk)
    {
        String manv=tk.getManv();
        String pass=tk.getPass();
        int tt=tk.getTrangthai();
        boolean rs=false;
        try{
            if(this.createConnection())
            {
                String sql="insert into taikhoan (manv,passwords,trangthai)"
                        + " values ('"+manv+"','"+pass+"',"+tt+")";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    rs=true;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean suataikhoan(taikhoan_DTO tk)
    {
        String manv=tk.getManv();
        String pass=tk.getPass();
        
        boolean rs=false;
        try{
            if(this.createConnection())
            {
                String sql="update taikhoan "
                        + "set passwords='"+pass+"' "
                        + "where manv='"+manv+"'";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    rs=true;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean xoataikhoan(taikhoan_DTO tk)
    {
        String manv=tk.getManv();
        String pass=tk.getPass();
        
        boolean rs=false;
        try{
            if(this.createConnection())
            {
                String sql="delete from taikhoan "
                        + "where manv= '"+manv+"'";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0) 
                {
                    rs=true;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean doitrangthai(String manv)
    {
        boolean rs=false;
        
        try{
            if(this.kiemtratt(manv)==1)
            {
                if(this.createConnection())
            {
                String sql="update taikhoan set trangthai=0 where manv='"+manv+"'";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    rs=true;
                }
            }
            }
            else
            {
                 if(this.createConnection())
            {
                String sql="update taikhoan set trangthai=1 where manv='"+manv+"'";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    rs=true;
                }
            }
            
            }
            
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
     public int kiemtratt(String manv)
    {
        int kq=0;
        if(this.createConnection())
        {
            try
            {
                String sql="select trangthai\n" + "from taikhoan\n" + "where MANV='"+manv+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    kq=rs.getInt("trangthai");
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
        taikhoan_DAL tk=new taikhoan_DAL();
        taikhoan_DTO tkDTO=new taikhoan_DTO();
        tkDTO.setManv("NV_0013");
        tkDTO.setPass("000000");
        System.out.println(tk.xoataikhoan(tkDTO));
        
    }
}
