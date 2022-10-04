/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DTO.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Hong Xuan
 */
public class phieunhapkho_DAL {
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
    public ArrayList<phieunhapkho_DTO> getall()
    {
        ArrayList<phieunhapkho_DTO> arr=new ArrayList<phieunhapkho_DTO>();
        if(this.createconnection())
        {
            try{
                String sql="select * \n" +
                            "from phieunhapkho";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    phieunhapkho_DTO pnk=new phieunhapkho_DTO();
                    pnk.setMank(rs.getString("MANK"));
                    pnk.setManv(rs.getString("MANV"));
                    pnk.setNgaynk(rs.getString("ngaynk"));
                    arr.add(pnk);
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
        return arr;
    }
    public boolean hasmank(String mank)
    {
        boolean kq=false;
        if(this.createconnection())
        {
            try
            {
                String sql="select *\n" +
                            "from phieunhapkho\n" +
                            "where mank='"+mank+"'";
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
    public boolean them(phieunhapkho_DTO pnk)
    {
        boolean kq=false;
        
        if(this.createconnection())
        {
            String mank=pnk.getMank();
            String manv=pnk.getManv();
            String ngaynk=pnk.getNgaynk();
            try
            {
                String sql="insert into PHIEUNHAPKHO values('"+mank+"','"+manv+"','"+ngaynk+"')";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
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
        phieunhapkho_DAL a=new phieunhapkho_DAL();
        phieunhapkho_DTO pnk=new phieunhapkho_DTO();
        pnk.setMank("NK_0008");
        pnk.setManv("NV_0002");
        pnk.setNgaynk("2021-02-02");
        System.out.println(a.them(pnk));
    }
}
