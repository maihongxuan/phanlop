/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import java.util.*;
import DAL.*;
import DTO.*;
/**
 *
 * @author Hong Xuan
 */
public class thuoc_BLL {
    thuoc_DAL thuoc=new thuoc_DAL();
    public ArrayList<thuoc_DTO> getall()
    {
        return thuoc.getall();
    }
    public ArrayList<String> getallten()
    {
        return thuoc.getallten();
    }
    public double getgia(String tenthuoc)
    {
        return thuoc.getgia(tenthuoc);
    }
    public String getmathuoc(String tenthuoc)
    {
        return thuoc.getmathuoc(tenthuoc);
    }
    public String getten(String mathuoc)
    {
        return thuoc.getten(mathuoc);
    }
    public int getsl(String mathuoc)
    {
        return thuoc.getsl(mathuoc);
    }
    public boolean kiemtrathuoc(String mathuoc,int k)
    {
        return thuoc.kiemtrathuoc(mathuoc, k);
    }
    public boolean capnhatsl(String mathuoc,int slmoi)
    {
        return thuoc.capnhatsl(mathuoc, slmoi);
    }
    public boolean hasthuoc(String mathuoc)
    {
        return thuoc.hasthuoc(mathuoc);
    }
    public boolean them(thuoc_DTO thuocDTO)
    {
        if(thuoc.hasthuoc(thuocDTO.getMathuoc()))
        {
            return false;
        }
        if(thuoc.them(thuocDTO))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public ArrayList<thuoc_DTO> thongketheothuoc(String ngaybatdau,String ngaykt)
    {
        return thuoc.tktheothuoc( ngaybatdau, ngaykt);
    }
}
