/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DTO.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hong Xuan
 */
public class thuoc_DAL {
    Connection con=null;
    String dburl="";
    public boolean creatconnection()
    {
        
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             dburl="jdbc:sqlserver://localhost:1433;"
                     + "DatabaseName=QUANLIPHONGKHAM";
             con=DriverManager.getConnection(dburl,"sa","sa");
             return true;
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.out.println(ex);
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
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    public ArrayList<thuoc_DTO> getall()
    {
        ArrayList<thuoc_DTO> thuoc=new ArrayList<thuoc_DTO>();
        if(this.creatconnection())
        {
            try{
                String sql="SELECT * FROM THUOC";
                Statement stm=con.createStatement();
                ResultSet rs =stm.executeQuery(sql);
                while(rs.next())
                {
                    thuoc_DTO thuocDTO=new thuoc_DTO();
                    thuocDTO.setMathuoc(rs.getString("Mathuoc"));
                    thuocDTO.setTenthuoc(rs.getString("TENTHUOC"));
                    thuocDTO.setGia(rs.getDouble("GIA"));
                    thuocDTO.setSl(rs.getInt("SL"));
                    thuoc.add(thuocDTO);
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
        }
        return thuoc;
    }
    public ArrayList<String> getallten()
    {
        ArrayList<String> arr=new ArrayList<String>();
        if(this.creatconnection())
        {
            try{
                String sql="SELECT tenthuoc\n" +
                            "FROM THUOC";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    String a=rs.getString("tenthuoc");
                    arr.add(a);
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
    public double getgia(String tenthuoc)
    {
        double gia=0;
         if (this.creatconnection()) {
            try {
                String sql = "SELECT top(1) t.GIA \n" +
                "FROM CHITIETHOADON ct , thuoc t\n" +
                "where t.tenTHUOC=N'"+tenthuoc+"'";
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
    public String getmathuoc(String tenthuoc)
    {
        String mathuoc="";
        if(this.creatconnection())
        {
            try{
                String sql="select MATHUOC\n" +
                            "from THUOC\n" +
                            "where TENTHUOC='"+tenthuoc+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    mathuoc=rs.getString("MATHUOC");
                    
                }
                else
                {
                    return "không tìm thấy mã thuốc";
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
        return mathuoc;
    }
    public String getten(String mathuoc)
    {
        String tenthuoc="";
        if(this.creatconnection())
        {
            try{
                String sql="select tenthuoc\n" +
                            "from THUOC\n" +
                            "where mathuoc ='"+mathuoc+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    tenthuoc=rs.getString("tenthuoc");
                    
                }
                else
                {
                    return "không tìm thấy mã thuốc";
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
        return tenthuoc;
    }
    public boolean hasthuoc(String mathuoc)
    {
        boolean kq=false;
        if(this.creatconnection())
        {
            try
                {
                    String sql="select *\n" +
                                "from THUOC\n" +
                                "where MATHUOC='"+mathuoc+"'";
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
    public boolean kiemtrathuoc(String mathuoc,int k)
    {
        boolean kiemtra=false;
        if(this.creatconnection())
        {
            try{
                String sql="select SL\n" +
                            "from THUOC\n" +
                            "where mathuoc='"+mathuoc+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    int sl=rs.getInt("SL");
                    if(sl>=k)
                    {
                        return true;
                    }
                }
                else{
                    return false;
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
        return kiemtra;
    }
    public int getsl(String mathuoc)
    {
        int sl=0;
                if(this.creatconnection())
                {
                    try{
                        String sql="select SL\n" +
                                    "from THUOC\n" +
                                    "where MATHUOC='"+mathuoc+"'";
                        Statement stm=con.createStatement();
                        ResultSet rs=stm.executeQuery(sql);
                        if(rs.next())
                        {
                            sl=rs.getInt("SL");
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
                return sl;
    }
    public boolean  capnhatsl(String mathuoc,int slmoi)
    {
        boolean kq=false;
        if(this.creatconnection())
        {
            try{
                String sql="update THUOC\n" +
                            "set SL="+slmoi+"\n" +
                            "where MATHUOC='"+mathuoc+"'";
                Statement stm=con.createStatement();
                int up=stm.executeUpdate(sql);
                if(up>0)
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
    public boolean them(thuoc_DTO thuoc)
    {
        boolean kq=false;
        if(this.creatconnection())
        {
            String mathuoc=thuoc.getMathuoc();
            String tenthuoc=thuoc.getTenthuoc();
            double gia=thuoc.getGia();
            int sl=thuoc.getSl();
            try
            {
                String sql="insert into thuoc values"
                        + "('"+mathuoc+"',N'"+tenthuoc+"',"+gia+","+sl+")";
                Statement stm=con.createStatement();
                int i=stm.executeUpdate(sql);
                if(i>0)
                {
                    kq=true;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally
            {
                this.closeconnection();
            }
        }
        return kq;
    }
    public ArrayList<thuoc_DTO> tktheothuoc(String ngaybatdau,String ngaykt)
    {
        ArrayList<thuoc_DTO> arr=new ArrayList<thuoc_DTO>();
        if(this.creatconnection())
        {
            try
            {
                String sql="select cthd.mathuoc, SUM(cthd.SL) as soluong\n" +
                            "from chitiethoadon cthd ,HOADON hd\n" +
                            "where hd.MAHD=cthd.MAHD and  hd.NGAY between '"+ngaybatdau+"' and '"+ngaykt+"'\n" +
                            "group by mathuoc";
                 Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    
                    thuoc_DTO thuoc=new thuoc_DTO();
                    String mathuoc=rs.getString("Mathuoc");
                    String tenthuoc=this.getten(mathuoc);
                    int sl=rs.getInt("soluong");
                    double gia=this.getgia(tenthuoc);
                    thuoc.setMathuoc(mathuoc);
                    thuoc.setTenthuoc(tenthuoc);
                    thuoc.setSl(sl);
                    thuoc.setGia(gia);
                    arr.add(thuoc);
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally
            {
                this.closeconnection();
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        thuoc_DAL thuoc=new thuoc_DAL();
        //System.out.println(thuoc.getall());
        System.out.println(thuoc.tktheothuoc("2021-05-01","2022-01-01"));
    }
}
