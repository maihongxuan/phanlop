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
public class dvbn_DAL {
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
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
            return false;
        }
    }
    public void closeconnection()
    {
        try{
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
    public boolean hasdvbn(String mabn,String madv,String mahd)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            try{
                String sql="select * \n" +
                            "from BENHNHANDICHVU\n" +
                            "where MABN='"+mabn+"' and MADV='"+madv+"' and MAHD='"+mahd+"'";
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
    public boolean them(dvbn_DTO dichvu)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            String mabn=dichvu.getMabn();
            String madv=dichvu.getMadv();
            String mahd=dichvu.getMahd();
            try{
                String sql="insert into BENHNHANDICHVU values ('"+mabn+"','"+madv+"','"+mahd+"')";
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
                this.closeconnection();
            }
        }
        return kq;
    }
    public static void main(String[] args) {
        dvbn_DAL dichvu=new dvbn_DAL();
    }
}
