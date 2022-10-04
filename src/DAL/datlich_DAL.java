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
public class datlich_DAL {
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
    public ArrayList <datlich_DTO> getall()
    {
        ArrayList <datlich_DTO> dlDTO=new ArrayList();
           if (this.createConnection() == true) {
            ResultSet rs = null;
            try {
                String sql = "select * from DATLICH";
                Statement s = con.createStatement();
                rs = s.executeQuery(sql);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                while (rs.next()) {
                    datlich_DTO dl=new datlich_DTO();
                dl.setMabn(rs.getString("MABN"));
                dl.setManv(rs.getString("MANV"));
                dl.setNgaydatlich(rs.getString("NGAY"));
                dl.setGiodatlich(rs.getString("GIO"));
                dlDTO.add(dl);
                }
            } catch (Exception e) {
                System.out.println("hello Mai Hồng Xuân" + e);
            }
            finally{
                this.closeConnection();
            }
        }
        
        return dlDTO;
    }
    public boolean themdlchuakham(datlich_DTO dl,benhnhan_DTO bnDTO)
    {
        benhnhan_DAL bnDAL=new benhnhan_DAL();
        String mabn=dl.getMabn();
        String manv=dl.getManv();
        String ngay=dl.getNgaydatlich();
        String gio=dl.getGiodatlich();
        boolean rs=false;
        if(this.createConnection()==true&& bnDAL.them(bnDTO)==true)
        {
            try{
                String sql="insert into datlich(mabn,manv,ngay,gio) "
                        + "values('"+mabn+"','"+manv+"','"+ngay+"','"+gio+"') ";
                Statement stm = con.createStatement();
                int i = stm.executeUpdate(sql);
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
    public boolean themdldakham(datlich_DTO dl)
    {
        String mabn=dl.getMabn();
        String manv=dl.getManv();
        String ngay=dl.getNgaydatlich();
        String gio=dl.getGiodatlich();
        boolean rs=false;
        if(this.createConnection()==true/*&& bn.them(bnDTO)==true*/)
        {
            try{
                String sql="insert into datlich(mabn,manv,ngay,gio) "
                        + "values('"+mabn+"','"+manv+"','"+ngay+"','"+gio+"') ";
                Statement stm = con.createStatement();
                int i = stm.executeUpdate(sql);
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
    public benhnhan_DTO timkiembn(String mabn) throws SQLException
    {
        benhnhan_DTO bnDTO=new benhnhan_DTO();
        if(this.createConnection())
        {   
            Statement stm=con.createStatement();
            ResultSet rs=null;
            try{
                String sql="select * from benhnhan where MABN='"+mabn+"'";
                 rs=stm.executeQuery(sql);
                if(rs.next())
                {
                bnDTO.setMabn(rs.getString("MABN"));
                bnDTO.setTenbn(rs.getString("TENBN"));
                bnDTO.setNamsinh(rs.getString("NAMSINH"));
                bnDTO.setGioitinh(rs.getString("GIOITINH"));
                bnDTO.setDiachi(rs.getString("DIACHI"));
                bnDTO.setTrieuchung(rs.getString("TRIEUCHUNG"));
                bnDTO.setTiensu(rs.getString("TIENSU"));
                bnDTO.setKetqua(rs.getString("KETQUA"));
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally{
                this.closeConnection();
            }
        }
        return bnDTO;
    }
    public nhanvien_DTO timkiemnhanvien(String manv) throws SQLException
    {
        nhanvien_DTO nvDTO = new nhanvien_DTO();
        if(this.createConnection())
        {   
            Statement stm=con.createStatement();
            ResultSet rs=null;
            try{
                String sql="select * from nhanvien where manv='"+manv+"'";
                 rs=stm.executeQuery(sql);
                if(rs.next())
                {
                    
                    nvDTO.setManv(rs.getString("MANV"));
                    nvDTO.setTennv(rs.getString("TENNV"));
                    nvDTO.setNgaysinh(rs.getString("NAMSINH"));
                    nvDTO.setChucvu(rs.getString("CHUCVU"));
                    nvDTO.setGioitinh(rs.getString("GIOITINH"));
                    nvDTO.setGmail(rs.getString("GMAIL"));
                    nvDTO.setSdt(rs.getString("SDT"));
                    nvDTO.setDiachi(rs.getString("DIACHI"));
                }
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
            finally{
                this.closeConnection();
            }
        }
        return nvDTO;
    }
    public boolean hasdatlich(String mabn,String manv)
    {
       
        boolean rs=false;
        if(this.createConnection())
        {
            ResultSet i=null;
            try{
               String sql="select * from datlich where mabn='"+mabn+"' and manv='"+manv+"'";
               Statement stm=con.createStatement();
               i=stm.executeQuery(sql);
               rs=i.next();
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
    public boolean sua(datlich_DTO dlDTO)
    {
        boolean rs=false;
        String mabn=dlDTO.getMabn();
        String manv=dlDTO.getManv();
        String ngay=dlDTO.getNgaydatlich();
        String gio=dlDTO.getGiodatlich();
        if(this.createConnection())
        {
            try{
            String sql="UPDATE datlich "
                   +  "SET NGAY='"+ngay+"', GIO='"+gio+"'"
                           + "where mabn ='"+mabn+"' and manv='"+manv+"'";
            Statement stm=con.createStatement();
            int i=stm.executeUpdate(sql);
            if(i>0)
            {
                rs=true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        finally{
            this.closeConnection();
        }
        }
        return rs;
    }
    public boolean xoa(datlich_DTO dlDTO)
    {
        boolean rs=false;
        String mabn=dlDTO.getMabn();
        String manv=dlDTO.getManv();
        String ngay=dlDTO.getNgaydatlich();
        String gio=dlDTO.getGiodatlich();
        if(this.createConnection())
        {
            try{
            String sql="DELETE FROM datlich " +
                   "where mabn ='"+mabn+"' and manv='"+manv+"'";
            Statement stm=con.createStatement();
            int i=stm.executeUpdate(sql);
            if(i>0)
            {
                rs=true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        finally{
            this.closeConnection();
        }
        }
        return rs;
        
    }
    public static void main(String[] args) {
        datlich_DAL dt=new datlich_DAL();
        datlich_DTO dlDTO=new datlich_DTO();
        dlDTO.setMabn("BN_0021");
        dlDTO.setManv("NV_0016");
        dlDTO.setNgaydatlich("2001-09-10");
        dlDTO.setGiodatlich("00:00");
       // System.out.println(dt.themdlchuakham(bn, nv, "2021-01-01", "9:00"));
        System.out.println(dt.xoa(dlDTO));
    }
}
