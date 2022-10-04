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
public class chitiethoadon_BLL {
    chitiethoadon_DAL  cthd_DAL=new chitiethoadon_DAL();
    public String gettenthuoc(String mathuoc)
    {
        return cthd_DAL.gettenthuoc(mathuoc);
    }
    public ArrayList<chitiethoadon_DTO> theoma(String mahd)
    {
        return cthd_DAL.theoma(mahd);
    }
    public double getgia(String mathuoc)
    {
        return cthd_DAL.getgia(mathuoc);
    }
    public boolean them(chitiethoadon_DTO hd_DTO)
    {
        if(cthd_DAL.hascthd(hd_DTO.getMahd(),hd_DTO.getMathuoc()))
        {
            return false;
        }
        if(cthd_DAL.them(hd_DTO))
        {
            return true;
        }   
        else
        {
            return false;
        }
    }
    public boolean hascthd(String mahd,String mathuoc)
    {
        return cthd_DAL.hascthd(mahd, mathuoc);
    }
    public ArrayList<chitiethoadon_DTO> thongketheohd(String ngaybd,String ngaykt)
    {
        return cthd_DAL.thongketheohd(ngaybd, ngaykt);
    }
    public String getNgay(String mahd)
    {
        return cthd_DAL.getngay(mahd);
    }
}
