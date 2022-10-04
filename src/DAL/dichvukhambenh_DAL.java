/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.sql.*;
import java.util.ArrayList;
import DTO.*;
/**
 *
 * @author Hong Xuan
 */
public class dichvukhambenh_DAL {
    Connection con=null;
    String dburl="";
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
        catch(SQLException | ClassNotFoundException ex )
        {
            System.out.println(ex);
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
    public ArrayList<String> gettendv()
    {
        ArrayList<String> name=new ArrayList<String>();
        if(this.createconnection())
        {
            try{
                String sql="SELECT tendv\n" +
                            "from dichvukhambenh";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    String a=rs.getString("tendv").trim();
                    name.add(a);
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
        return name;
    }
    public double getgia(String tendv)
    {
        double gia=0;
        if(this.createconnection())
        {
            try{
                String sql="SELECT  GIADV\n" +
                            "from dichvukhambenh\n" +
                            "where TENDV=N'"+tendv+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    gia=rs.getDouble("giadv");
                   
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
        return gia;
    }
    public String tongdv(String mabn)
    {
        String tong="";
        if(this.createconnection())
        {
            try{
                String sql="select dv.TENDV\n" +
                            "from DICHVUKHAMBENH dv, BENHNHANDICHVU bn\n" +
                            "where dv.MADV=bn.MADV and bn.MABN='"+mabn+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    tong+=rs.getString("TENDV")+" , ";
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally{
                this.closeconnection();
            }
        }
        return tong;
    }
    public String tongdvtheomahd(String mahd)
    {
        String tong="";
        if(this.createconnection())
        {
            try{
                String sql="select dv.TENDV\n" +
                            "from DICHVUKHAMBENH dv, BENHNHANDICHVU bn\n" +
                            "where dv.MADV=bn.MADV and bn.mahd='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    tong+=rs.getString("TENDV")+" , ";
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            finally{
                this.closeconnection();
            }
        }
        return tong;
    }
    public double tonggiatheomahd(String mahd)
    {
        double tonggia=0;
        if(this.createconnection())
        {
            try
            {
                String sql="select dv.GIADV\n" +
                            "from DICHVUKHAMBENH dv, BENHNHANDICHVU bn\n" +
                            "where dv.MADV=bn.MADV and bn.mahd='"+mahd+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    tonggia+=rs.getDouble("GIADV");
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
        return tonggia;
    }
    public double tonggia(String mabn)
    {
        double tonggia=0;
        if(this.createconnection())
        {
            try
            {
                String sql="select dv.GIADV\n" +
                            "from DICHVUKHAMBENH dv, BENHNHANDICHVU bn\n" +
                            "where dv.MADV=bn.MADV and bn.MABN='"+mabn+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    tonggia+=rs.getDouble("GIADV");
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
        return tonggia;
    }
    public String getMa(String tendv)//tìm mã khi có tên
    {
        String ma="";
        if(this.createconnection())
        {
            try
            {
                String sql="select MADV\n" +
                            "from DICHVUKHAMBENH\n" +
                            "where TENDV=N'"+tendv+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    ma=rs.getString("madv");
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
        return ma;
    }
    public static void main(String[] args) {
        dichvukhambenh_DAL dvkb=new dichvukhambenh_DAL();
        System.out.println(dvkb.getMa("Đo điện tim"));
    }
}
