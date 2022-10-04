/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DTO.benhnhan_DTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hong Xuan
 */
public class benhnhan_DAL {
    Connection con=null;
    String dburl="";
    
    public boolean createConnection()
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
    public void closeConnection()
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
    public ArrayList<benhnhan_DTO> getall()
    {
        ArrayList <benhnhan_DTO> arr =new ArrayList<benhnhan_DTO>();
           if(this.createConnection()==true)
           {
               ResultSet rs=null;
             try
            {
                String sql="select * from BENHNHAN";
                Statement s=con.createStatement();
                rs=s.executeQuery(sql);
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }
             try {
            while (rs.next()) {
                benhnhan_DTO bnDTO=new benhnhan_DTO();
                bnDTO.setMabn(rs.getString("MABN"));
                bnDTO.setTenbn(rs.getString("TENBN"));
                bnDTO.setNamsinh(rs.getString("NAMSINH"));
                bnDTO.setGioitinh(rs.getString("GIOITINH"));
                bnDTO.setDiachi(rs.getString("DIACHI"));
                bnDTO.setTrieuchung(rs.getString("TRIEUCHUNG"));
                bnDTO.setTiensu(rs.getString("TIENSU"));
                bnDTO.setKetqua(rs.getString("KETQUA"));
                arr.add(bnDTO);
            }
        } catch (Exception e) {
                 System.out.println("hello Mai Hồng Xuân"+e);
        }
             this.closeConnection();
           }
           return arr;
    }
    public boolean hasbenhnhan(String id)
    {
        boolean rs=false;
       
       try{
           if(this.createConnection()==true)
           {
           String sql="SELECT MABN FROM BENHNHAN WHERE MABN='"+id+"'";
           Statement st=con.createStatement();
           ResultSet result=st.executeQuery(sql);
           rs=result.next();
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
    public boolean them(benhnhan_DTO bn)
    {
        boolean rs=false;
        try{
            if(this.createConnection())
            {
                 String mabn=bn.getMabn();
            String tenbn=bn.getTenbn();
            String ns=bn.getNamsinh();
            String gt=bn.getGioitinh();
            String dc=bn.getDiachi();
            String tc=bn.getTrieuchung();
            String ts=bn.getTiensu();
            String kq=bn.getKetqua();
           String sql="insert into BENHNHAN (MABN,TENBN,NAMSINH,GIOITINH,DIACHI,TRIEUCHUNG,TIENSU,KETQUA) "
                   + "values (N'"+mabn+"',N'"+tenbn+"',N'"+ns+"',N'"+gt+"',N'"+dc+"',N'"+tc+"',N'"+ts+"',N'"+kq+"')";
           
           Statement stm=con.createStatement();
           int i=stm.executeUpdate(sql);
           if(i>0)
           {
               rs=true;
           }
            }
           
//           ps.setString(1,bn.getMabn());
//           ps.setString(2,bn.getTenbn());
//           ps.setString(3,bn.getNamsinh());
//           ps.setString(5,bn.getGioitinh());
//           ps.setString(4,bn.getDiachi());
//           ps.setString(6,bn.getTrieuchung());
//           ps.setString(7,bn.getTiensu());
//           ps.setString(8,bn.getKetqua());
            
        }
        catch(SQLException ex)
        {
            
            System.out.println(ex);
            System.out.println("hi pro!");
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean sua(benhnhan_DTO bn)
    {
       boolean rs=false;
        try{
            if(this.createConnection())
            {
            String mabn=bn.getMabn();
            String tenbn=bn.getTenbn();
            String ns=bn.getNamsinh();
            String gt=bn.getGioitinh();
            String dc=bn.getDiachi();
            String tc=bn.getTrieuchung();
            String ts=bn.getTiensu();
            String kq=bn.getKetqua();
           String sql="UPDATE BENHNHAN "
                   +  "SET TENBN =N'"+tenbn+"', NAMSINH=N'"+ns+"', GIOITINH=N'"+gt+"', DIACHI=N'"+dc
                   +"', TRIEUCHUNG=N'"+tc+"', TIENSU=N'"+ts+"', KETQUA=N'"+kq+"' "
                           + "WHERE MABN='"+mabn+"'";
                   
           
           Statement stm=con.createStatement();
           int up=stm.executeUpdate(sql);
           if(up>0)
           {
               rs=true;
           }
        }
           
        }
         catch(SQLException e)
        {
                 System.out.println(e);   
        }
        finally{
            this.closeConnection();
        }
        return rs;
    }
    public boolean xoa(String mabn)
    {
        boolean rs=false;
        try{
            if(createConnection())
            {
                String sql="DELETE FROM BENHNHAN "
                    + "WHERE MABN='"+mabn+"'";
            Statement stm=con.createStatement();
            int i=stm.executeUpdate(sql);
            if(i>0)
            {
                return true;
            }
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally{
            this.closeConnection();
        }
        return rs;
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
        String mabn="";
        String tenbn="";
        String nsbn=null;
        String gt="";
        String dc="";
        String tc="";
        String ts="";
        String kq="";
        System.out.println(sheet.getLastRowNum());
        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            row = sheet.getRow(i);
            mabn =  row.getCell(0).getStringCellValue().trim();

            tenbn = row.getCell(1).getStringCellValue().trim();

            nsbn =  row.getCell(2).getStringCellValue().trim();

            gt = row.getCell(3).getStringCellValue().trim();

            dc =  row.getCell(4).getStringCellValue().trim();

            tc =  row.getCell(5).getStringCellValue().trim();

            ts = row.getCell(6).getStringCellValue().trim();

            kq = row.getCell(7).getStringCellValue().trim();

            

            String sql_check = "SELECT * FROM benhnhan WHERE mabn='" + mabn+"'";
            if(this.createConnection())
            {
                Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql_check);
            if (rs.next()==false){
                
                 String sql="insert into BENHNHAN (MABN,TENBN,NAMSINH,GIOITINH,DIACHI,TRIEUCHUNG,TIENSU,KETQUA) "
                   + "values (N'"+mabn+"',N'"+tenbn+"',N'"+nsbn+"',N'"+gt+"',N'"+dc+"',N'"+tc+"',N'"+ts+"',N'"+kq+"')";
                
                int l=stm.executeUpdate(sql);
                if(l>0){
                    result= "import thành công";
                }

            } else {
                 
                 String sql="UPDATE BENHNHAN "
                   +  "SET TENBN =N'"+tenbn+"', NAMSINH=N'"+nsbn+"', GIOITINH=N'"+gt+"', DIACHI=N'"+dc
                   +"', TRIEUCHUNG=N'"+tc+"', TIENSU=N'"+ts+"', KETQUA=N'"+kq+"' "
                           + "WHERE MABN='"+mabn+"'";
                   
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
    public String tenbn(String mabn)
    {
        String ten="";
        if(this.createConnection())
        {
            try{
                String sql="select TENBN\n" +
                            "from BENHNHAN\n" +
                            "where MABN='"+mabn+"'";
                Statement stm=con.createStatement();
                ResultSet rs=stm.executeQuery(sql);
                while(rs.next())
                {
                    ten=rs.getString("TENBN");
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
        return ten;
    }        
    public static void main(String[] args) throws IOException, SQLException {
        benhnhan_DAL a = new benhnhan_DAL();
        if(a.createConnection()==true)
        {
            System.out.println("kết nối thành công!");
        }
        System.out.println(a.tenbn("bn_0001"));
    }
}
