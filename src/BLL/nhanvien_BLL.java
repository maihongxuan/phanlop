/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import DTO.*;
import java.util.*;
/**
 *
 * @author Hong Xuan
 */
public class nhanvien_BLL {
    nhanvien_DAL nvDAL=new nhanvien_DAL();
    public ArrayList<nhanvien_DTO> getall()
    {
        return nvDAL.getall();
    }
    public String them(nhanvien_DTO nv)
    {
        if(nvDAL.hasnhanvien(nv.getManv()))
        {
            return "Mã bệnh nhân đã tồn tại!!!";
        }
        if(nvDAL.them(nv))
        {
            return "Thêm thành công";
        }
        else{
            return "Thêm thất bại!";
        }
    }
    public String sua(nhanvien_DTO nv)
    {
        if(nvDAL.hasnhanvien(nv.getManv())==false)
        {
            return "Mã bệnh nhân Không tồn tại tồn tại!!!";
        }
        if(nvDAL.sua(nv))
        {
            return "Sửa Thành Công";
        }
        else
        {
          return "Sửa không thành công" ; 
        }
    }
    public String xoa(nhanvien_DTO nv)
    {
        if(nvDAL.hasnhanvien(nv .getManv())==false)
        {
            return "Mã bệnh nhân Không tồn tại tồn tại!!!";
        }
        if(nvDAL.xoa(nv.getManv()))
        {
            return "Xóa Thành Công";
        }
        else
        {
          return "Xóa không thành công" ; 
        }
    }
    public String importt()
    {
        return nvDAL.importt();
    }
    public boolean hasnv(String manv)
    {
        return nvDAL.hasnhanvien(manv);
    }
    public ArrayList<chitiethoadon_DTO> tktheomanv(String manv,String ngaybd,String ngaykt)
    {
        return nvDAL.tktheonv(manv, ngaybd, ngaykt);
    }
}
