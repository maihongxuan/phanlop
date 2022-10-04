/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.*;
import DAL.*;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author Hong Xuan
 */
public class datlich_BLL {
    datlich_DAL dlDAL=new datlich_DAL();
    public ArrayList<datlich_DTO> getall()
    {
        ArrayList<datlich_DTO> dtBLL=new ArrayList();
        return dlDAL.getall();
    }
    public benhnhan_DTO timkiembn(String mabn) throws SQLException
    {
            return dlDAL.timkiembn(mabn);
    }
    public nhanvien_DTO timkiemnv(String manv) throws SQLException
    {
        return dlDAL.timkiemnhanvien(manv);
    }
    public String themlichdakham(datlich_DTO dl)
    {
        if(dlDAL.hasdatlich(dl.getMabn(),dl.getManv()))
        {
            return "Lịch đã được đặt";
        }
        if(dlDAL.themdldakham(dl))
        {
            return "thêm thành công!!";
        }
        else{
            return "Đặt lịch không thành công!!";
        }
    }

    public String themlichchuakham(datlich_DTO dl,benhnhan_DTO bn)
    {
        if(dlDAL.hasdatlich(dl.getMabn(),dl.getManv()))
        {
            return "Lịch đã được đặt";
        }
        if(dlDAL.themdlchuakham(dl, bn))
        {
            return "Đặt lịch thành công";
        }
        else
        {
            return "Đặt lịch không thành công";
        }
    }
    public String sualich(datlich_DTO dl)
    {
        if(dlDAL.hasdatlich(dl.getMabn(),dl.getManv())==false)
        {
            return "Lịch chưa được đặt";
        }
        if(dlDAL.sua(dl))
        {
            return "Sửa lịch thành công";
        }
        else
        {
            return "Sửa lịch không thành công";
        }
    }
    public String xoalich(datlich_DTO dl)
    {
        if(dlDAL.hasdatlich(dl.getMabn(),dl.getManv())==false)
        {
            return "Lịch chưa được đặt";
        }
        if(dlDAL.xoa(dl))
        {
            return "Xóa lịch thành công";
        }
        else
        {
            return "Xóa lịch không thành công";
        }
    }
    public static void main(String[] args) throws SQLException {
        datlich_BLL dl=new datlich_BLL();
        datlich_DAL dlDAL=new datlich_DAL();
        datlich_DTO dlDTO=new datlich_DTO();
        dlDTO.setMabn("BN_0021");
        dlDTO.setManv("NV_0016");
        dlDTO.setNgaydatlich("2021-02-02");
        dlDTO.setGiodatlich("19:00");
        //System.out.println(dl.timkiem("BN_0021"));ư\
        System.out.println(dl.sualich(dlDTO));
        
    }
}
