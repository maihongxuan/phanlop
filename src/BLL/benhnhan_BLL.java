/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.*;
import DAL.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Hong Xuan
 */
public class benhnhan_BLL {
    benhnhan_DAL bnDAL=new benhnhan_DAL();
    public ArrayList<benhnhan_DTO> getall()
    {
       // ArrayList<benhnhan_DTO> arrbn=new ArrayList();       
        return bnDAL.getall();
    }
     public String them(benhnhan_DTO bn)
    {
        if(bnDAL.hasbenhnhan(bn.getMabn()))
        {
            return "Mã bệnh nhân đã tồn tại!!!";
        }
        if(bnDAL.them(bn))
        {
            return "Thêm thành công";
        }
        else{
            return "Thêm thất bại!";
        }
    }
    public String sua(benhnhan_DTO bn)
    {
        if(bnDAL.hasbenhnhan(bn.getMabn())==false)
        {
            return "Mã bệnh nhân Không tồn tại tồn tại!!!";
        }
        if(bnDAL.sua(bn))
        {
            return "Sửa Thành Công";
        }
        else
        {
          return "Sửa không thành công" ; 
        }
    }
    public String xoa(benhnhan_DTO bn)
    {
         if(bnDAL.hasbenhnhan(bn.getMabn())==false)
        {
            return "Mã bệnh nhân Không tồn tại tồn tại!!!";
        }
        if(bnDAL.xoa(bn.getMabn()))
        {
            return "Xóa Thành Công";
        }
        else
        {
          return "Xóa không thành công" ; 
        }
    }
    public String importt() throws IOException, SQLException
    {
        return bnDAL.importt();
    }
    public String tenbn(String mabn)
    {
        return bnDAL.tenbn(mabn);
    }
    public boolean hasbn(String mabn)
    {
        return bnDAL.hasbenhnhan(mabn);
    }
    public static void main(String[] args) {
        benhnhan_BLL bnBLL=new benhnhan_BLL();
        benhnhan_DTO bn= new benhnhan_DTO();
        bn.setMabn("BN_0021");
        System.out.println(bnBLL.xoa(bn));
    }
}
