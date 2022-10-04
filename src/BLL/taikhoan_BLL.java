/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DAL.*;
import DTO.*;
import java.util.ArrayList;
/**
 *
 * @author Hong Xuan
 */
public class taikhoan_BLL {
    taikhoan_DAL tkDAL=new taikhoan_DAL();
    public ArrayList<taikhoan_DTO> getall()
    {
        return tkDAL.getall();
    }
    public String them(taikhoan_DTO tk)
    {
        if(tkDAL.hasnhanvien(tk.getManv()))
        {
            return " Tài khoản nhân viên đã tồn tại!!";
        }
        if(tkDAL.themtaikhoan(tk))
        {
            return "Thêm tài khoản thành công";
        }
        else
        {
            return "Thêm tài khoản thất bại";
        }
        
    }
    public String sua(taikhoan_DTO tk)
    {
        if(tkDAL.hasnhanvien(tk.getManv())==false)
        {
            return "Tài khoản không tồn tại !!";
        }
        if(tkDAL.suataikhoan(tk))
        {
            return "Sửa thành công";
        }
        else{
            return "Sửa không thành công";
        }
    }
    public String xoa(taikhoan_DTO tk)
    {
         if(tkDAL.hasnhanvien(tk.getManv())==false)
        {
            return "Tài khoản không tồn tại !!";
        }
        if(tkDAL.xoataikhoan(tk))
        {
            return "Xóa tài khoản thành công";
        }
        else{
            return "Xóa tài khoản không thành công";
        }
    }
    public int kiemtratt(String manv)
    {
        return tkDAL.kiemtratt(manv);
    }
    public String doitt(String manv)
    {
        if(tkDAL.doitrangthai(manv))
        {
            return "đã đổi trạng thái tài khoản";
        }
        else
        {
            return "Đổi trạng thái không thành công!!";
        }
    }
    public static void main(String[] args) {
        taikhoan_BLL tk=new taikhoan_BLL();
        taikhoan_DTO tkDTO=new taikhoan_DTO();
        tkDTO.setManv("NV_0013");
        System.out.println(tk.xoa(tkDTO));
    }
}
