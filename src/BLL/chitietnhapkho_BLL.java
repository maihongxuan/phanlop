/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;
import DTO.*;
import DAL.*;
import java.util.ArrayList;
/**
 *
 * @author Hong Xuan
 */
public class chitietnhapkho_BLL {
    chitietnhapkho_DAL chitietnk=new chitietnhapkho_DAL();
    public ArrayList<chitietnhapkho_DTO> gettheomank(String mank)
    {
        return chitietnk.gettheomank(mank);
    }
    public boolean haschitiet(String mank,String mathuoc)
    {
        return chitietnk.haschitiet(mank, mathuoc);
        
    }
    public boolean them(chitietnhapkho_DTO ctnk)
    {
        if(chitietnk.haschitiet(ctnk.getMank(), ctnk.getMathuoc()))
        {
            return false;
        }
        if(chitietnk.them(ctnk)) return true;
        return false;
    }
}
