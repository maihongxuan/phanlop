/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hong Xuan
 */
public class nhanvien_DAL {

    Connection con = null;
    String dburl = "";

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

    public ArrayList<nhanvien_DTO> getall() {
        ArrayList<nhanvien_DTO> arr = new ArrayList<nhanvien_DTO>();
        if (this.createConnection() == true) {
            ResultSet rs = null;
            try {
                String sql = "select * from NHANVIEN";
                Statement s = con.createStatement();
                rs = s.executeQuery(sql);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                while (rs.next()) {
                    nhanvien_DTO nvDTO = new nhanvien_DTO();
                    nvDTO.setManv(rs.getString("MANV"));
                    nvDTO.setTennv(rs.getString("TENNV"));
                    nvDTO.setNgaysinh(rs.getString("NAMSINH"));
                    nvDTO.setChucvu(rs.getString("CHUCVU"));
                    nvDTO.setGioitinh(rs.getString("GIOITINH"));
                    nvDTO.setGmail(rs.getString("GMAIL"));
                    nvDTO.setSdt(rs.getString("SDT"));
                    nvDTO.setDiachi(rs.getString("DIACHI"));
                    arr.add(nvDTO);
                }
            } catch (Exception e) {
                System.out.println("hello Mai Hồng Xuân" + e);
            }
            this.closeConnection();
        }
        return arr;
    }
     public String importt() {
        String result=null;
        try {
        
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel", "xlsx");
        fc.setFileFilter(filter);
        File file = fc.getSelectedFile();
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row;
        String manv="";
        String tennv="";
        String ns=null;
        String gt="";
        String cv="";
        String dc="";
        String gmail="";
        String sdt="";
        System.out.println(sheet.getLastRowNum());
        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            manv =  row.getCell(0).getStringCellValue().trim();

            tennv = row.getCell(1).getStringCellValue().trim();

            ns =  row.getCell(2).getStringCellValue().trim();

            cv = row.getCell(3).getStringCellValue().trim();

            gt =  row.getCell(4).getStringCellValue().trim();

            gmail =  row.getCell(5).getStringCellValue().trim();

            sdt = row.getCell(6).getStringCellValue().trim();

            dc = row.getCell(7).getStringCellValue().trim();

            

            String sql_check = "SELECT * FROM nhanvien WHERE manv='" + manv+"'";
            if(this.createConnection())
            {
                Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql_check);
            /*129:kiểm tra xem mã nhân viên có tồn tại hay không
            +nếu không thì insert vào
            +nếu có thì update lại 
            */
            if (rs.next()==false){
                
                  String sql = "insert into NHANVIEN (MANV,TENNV,NAMSINH,CHUCVU,GIOITINH,GMAIL,SDT,DIACHI) "
                            + "values (N'" + manv + "',N'" + tennv + "',N'" + ns + "',N'" + cv + "',N'" + gt + "',N'" + gmail + "',N'" + sdt + "',N'" + dc + "')";
                
                int l=stm.executeUpdate(sql);
                if(l>0){
                    result= "import thành công";
                }

            } else {
                 
                 String sql="UPDATE NHANVIEN "
                   +  "SET TENNV =N'"+tennv+"', NAMSINH=N'"+ns+"', GIOITINH=N'"+gt+"', DIACHI=N'"+dc
                   +"', CHUCVU=N'"+cv+"', GMAIL='"+gmail+"', SDT='"+sdt+"' "
                           + "WHERE MANV='"+manv+"'";
                   
                int l=stm.executeUpdate(sql);
                if(l>0){
                    result= "import thành công";
                }
            }
            }
            
        }
       } catch (IOException ex) {
            result="import không thành công";
       } catch (SQLException ex) {
            result="import không thành công";
       }
    return result;
}
    public boolean them(nhanvien_DTO nv) {
        boolean rs = false;
        try {
            if(this.createConnection())
            {
                    String manv = nv.getManv();
                    String tennv = nv.getTennv();
                    String ns = nv.getNgaysinh();
                    String cv = nv.getChucvu();
                    String gt = nv.getGioitinh();
                    String dc = nv.getDiachi();
                    String gmail = nv.getGmail();
                    String sdt = nv.getSdt();
                    String sql = "insert into NHANVIEN (MANV,TENNV,NAMSINH,CHUCVU,GIOITINH,GMAIL,SDT,DIACHI) "
                            + "values (N'" + manv + "',N'" + tennv + "',N'" + ns + "',N'" + cv + "',N'" + gt + "',N'" + gmail + "',N'" + sdt + "',N'" + dc + "')";

                    Statement stm = con.createStatement();
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        rs = true;
                    }
            }
           
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.closeConnection();
        }
        return rs;
    }

    public boolean hasnhanvien(String id) {
        boolean rs = false;

        try {
            if (this.createConnection() == true) {
                String sql = "SELECT MANV FROM NHANVIEN WHERE MANV='" + id + "'";
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery(sql);
                rs = result.next();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            this.closeConnection();
        }
        return rs;
    }

    public boolean sua(nhanvien_DTO nv) {
        boolean rs = false;
        try {
            if (this.createConnection()) {
                    String manv = nv.getManv();
                    String tennv = nv.getTennv();
                    String ns = nv.getNgaysinh();
                    String cv = nv.getChucvu();
                    String gt = nv.getGioitinh();
                    String dc = nv.getDiachi();
                    String gmail = nv.getGmail();
                    String sdt = nv.getSdt();
                    //(MANV,TENNV,NAMSINH,CHUCVU,GIOITINH,GMAIL,SDT,DIACHI) 
                    String sql="UPDATE NHANVIEN "
                   +  "SET TENNV =N'"+tennv+"', NAMSINH=N'"+ns+"', GIOITINH=N'"+gt+"', DIACHI=N'"+dc
                   +"', CHUCVU=N'"+cv+"', GMAIL='"+gmail+"', SDT='"+sdt+"' "
                           + "WHERE MANV='"+manv+"'";
                   
           
           Statement stm=con.createStatement();
           int up=stm.executeUpdate(sql);
           if(up>0)
           {
               rs=true;
           }
                
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            this.closeConnection();
        }
        return rs;
    }

    public boolean xoa(String id) {
        boolean rs = false;
        try {
            if(this.createConnection())
            {
                String sql="DELETE FROM NHANVIEN "
                    + "WHERE MANV='"+id+"'";
            Statement stm=con.createStatement();
            int i=stm.executeUpdate(sql);
            if(i>0)
            {
                return true;
            }
            }
        } catch (Exception ex) {
            
            System.out.println(ex);
        } finally {
            this.closeConnection();
        }
        return rs;
    }
    public ArrayList<chitiethoadon_DTO> tktheonv(String manv,String ngaybd,String ngaykt)
    {
        ArrayList<chitiethoadon_DTO> arr=new ArrayList<chitiethoadon_DTO>();
        if(this.createConnection())
        {
            try
            {
                String sql="select cthd.*\n" +
                            "from hoadon hd,CHITIETHOADON cthd\n" +
                            "where hd.MAHD=cthd.MAHD and hd.MANV='"+manv+" ' and "
                        + "(hd.NGAY between '"+ngaybd+"' and '"+ngaykt+"')";
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
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
            finally
            {
                this.closeConnection();
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        nhanvien_DTO nv = new nhanvien_DTO();
        nhanvien_DAL nvDAL = new nhanvien_DAL();
//         System.out.println(nvDAL.hasnhanvien("NV_0001"));
        System.out.println(nvDAL.importt());
       
    }
}
