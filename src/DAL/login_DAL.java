/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DTO.*;
import java.sql.*;

/**
 *
 * @author Hong Xuan
 */
public class login_DAL {
    Connection con=null;
    String dburl;
    public boolean createconnection()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dburl="jdbc:sqlserver://localhost:1433;"
                    + "DatabaseName=QUANLIPHONGKHAM";
            con=DriverManager.getConnection(dburl,"sa","sa");
            return true;
        }
        catch(SQLException |ClassNotFoundException e)
        {
            System.out.println(e);
            return false;
        }
    }
    public void closeconnection()
    {
        try
        {
            if(con!=null)
            {
                con.close();
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    public int kiemtratt(String manv)
    {
        int kq=0;
        if(this.createconnection())
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
                this.closeconnection();
            }
        }
        return kq;
    }
    public boolean kiemtra(String manv,String pass)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            try
            {
                String sql="select *\n" +
                            "from TAIKHOAN\n" +
                            "where manv='"+manv+"' and PASSWORDS='"+pass+"' and trangthai="+1;
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
                this.closeconnection();
            }
        }
        return kq;
    }
    public static void main(String[] args) {
        login_DAL lg=new login_DAL();
        System.out.println(lg.createconnection());
    }
}
