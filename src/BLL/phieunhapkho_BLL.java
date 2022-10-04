/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.*;
import DAL.*;
import java.util.*;
/**
 *
 * @author Hong Xuan
 */
public class phieunhapkho_BLL {
    phieunhapkho_DAL pnkDAL=new phieunhapkho_DAL();
    public ArrayList<phieunhapkho_DTO> getall()
    {
        return pnkDAL.getall();
    }
    public boolean hasphieunk(String mank)
    {
        return pnkDAL.hasmank(mank);
    }
    public boolean them(phieunhapkho_DTO pnkDTO)
    {
        if(pnkDAL.hasmank(pnkDTO.getMank()))
        {
            return false;
        }
        if(pnkDAL.them(pnkDTO))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
