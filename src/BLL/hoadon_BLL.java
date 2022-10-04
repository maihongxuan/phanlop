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
public class hoadon_BLL {
    hoadon_DAL hd=new hoadon_DAL();
    public ArrayList<hoadon_DTO> getall()
    {
        return hd.getall();
    }
    public ArrayList<chitiethoadon_DTO> connectcthd(String mahd)
    {
        return hd.connectioncthd(mahd);
    }
    public boolean them(hoadon_DTO hdDTO)
    {
        if(hd.hasmahd(hdDTO.getMahd()))
        {
            return false;
        }
        if(hd.them(hdDTO))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean hashd(String mahd)
    {
        return hd.hashd(mahd);
    }
    
}
